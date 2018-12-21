package com.hong_world.common.base;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.hong_world.common.R;
import com.hong_world.common.utils.Compressor;
import com.hong_world.library.utils.FileUtils;
import com.hong_world.common.utils.GifSizeFilter;
import com.hong_world.common.utils.Glide4Engine;
import com.hong_world.common.utils.SDCardUtils;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;
import com.orhanobut.logger.Logger;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Date: 2018/10/18. 16:54
 * Author: hong_world
 * Description:
 * Version:
 */
public abstract class BaseUploadImageFragment<P extends BasePresenter, V extends ViewDataBinding> extends BaseFragment<P, V>
        implements BaseView {
    private static final int REQUEST_MULTIPLE_CODE_CHOOSE = 19;
    private static final int PHOTO_REQUEST_GALLERY = 20;
    private static final int PHOTO_REQUEST_GALLERY_CROP = 21;
    private static final int PHOTO_REQUEST_CUT = 22;
    private static final int PHOTO_REQUEST_CAMERA_CROP = 23;
    private static final int PHOTO_REQUEST_CAMERA_FILE = 24;
    public static final String CACHE_FILE_PATH = "/myFile";
    //拍照后的完整图片文件
    protected File tempFile;
    //拍照剪切后的图片文件
    protected File imageFile;
    //多选图片的URL集合
    protected List<Uri> mSelectedUrl;
    //多选图片的URL集合
    protected List<String> mSelectedPath;

    protected void upLoadMultipleUrlImage(List<Uri> selected) {
        // TODO: 获取多张图片
    }

    protected void upLoadMultiplePathImage(List<String> selected) {
        // TODO: 获取多张图片
    }

    protected void upLoadMultipleFileImage(List<File> selected) {
        // TODO: 获取多张图片
    }

    protected void upLoadImage(Bitmap bitmap) {
        // TODO: 一般用于获取上传头像的bitmap,更新头像显示
    }

    protected void upLoadImageFile(File file) {
        // TODO:  一般用于获取完整的拍照图片
    }

    /**
     * 多张图片选择
     */
    protected void selectMultipleImage() {
        selectMultipleImage(9);
    }

    /**
     * 多张图片选择
     *
     * @param maxSize 最多选择的个数
     */
    protected void selectMultipleImage(int maxSize) {
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .theme(R.style.Matisse_Dracula)
                .countable(false)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .maxSelectable(9)
                .originalEnable(true)
                .maxOriginalSize(10)
                .imageEngine(new Glide4Engine())
                .forResult(REQUEST_MULTIPLE_CODE_CHOOSE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                if (uri == null) return;
                compressImage(new File(uri.getPath()));
            }
        } else if (requestCode == PHOTO_REQUEST_GALLERY_CROP) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                if (uri == null) return;
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAMERA_CROP) {
            // 从相机返回的数据
            if (tempFile.length() == 0) {
                tempFile.delete();
                Logger.i("取消");
                return;
            }
            crop(null);
        } else if (requestCode == PHOTO_REQUEST_CAMERA_FILE) {
            // 从相机返回的数据
            if (tempFile.length() == 0) {
                tempFile.delete();
                Logger.i("取消");
                return;
            }
//            upLoadImageFile(tempFile);
            compressImage(tempFile);
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                //  Bitmap bitmap = data.getParcelableExtra("data");
                Bitmap bitmap = decodeFileAsBitmap(imageFile);
                if (bitmap == null) {
                    return;
                }
                upLoadImage(bitmap);
//                upLoadImageFile(imageFile);
                compressImage(imageFile);
            }
            try {
                // 将临时文件删除
                if (tempFile != null) {
                    boolean isDelete = false;
                    if (tempFile.exists()) {
                        isDelete = tempFile.delete();
                        Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(tempFile));
                        mActivity.sendBroadcast(media);
                    }
                    if (isDelete) {
                        Logger.e("临时图片文件删除");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_MULTIPLE_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelectedUrl = Matisse.obtainResult(data);
            mSelectedPath = Matisse.obtainPathResult(data);
            compressImage(mSelectedUrl);
            Logger.i("OnActivityResult :" + String.valueOf(Matisse.obtainOriginalState(data)));
        }
    }

    /**
     * 自带图库选择图片进行裁剪
     */
    protected void galleryCrop() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY_CROP);
    }

    /**
     * 自带图库选择图片
     */
    protected void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 拍照（拍照后进行图片裁剪）
     */
    protected void cameraCropPhoto() {
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (SDCardUtils.isSDCardEnable()) {
            tempFile = createCacheFile(System.currentTimeMillis() + ".jpg");
        } else {
            Logger.e("未找到存储卡，无法存储照片！");
        }
        if (tempFile == null) {
            return;
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri(tempFile));
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAMERA
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA_CROP);
    }

    /**
     * 拍照（直接获取拍照后的图片文件）
     */
    protected void cameraPhotoFile() {
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (SDCardUtils.isSDCardEnable()) {
            tempFile = createCacheFile(System.currentTimeMillis() + ".jpg");
        } else {
            Logger.e("未找到存储卡，无法存储照片！");
        }
        if (tempFile == null) {
            return;
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri(tempFile));
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAMERA
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA_FILE);
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    protected void crop(Uri uri) {
        imageFile = createCacheFile("userImage" + System.currentTimeMillis() + ".jpg");
        if (imageFile == null) {
            return;
        }
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri == null ? getImageContentUri(tempFile) : uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri(imageFile));//裁剪后图片保存位置
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 文件获取bitmap
     *
     * @param file
     * @return
     */
    public Bitmap decodeFileAsBitmap(File file) {
        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 文件Uri （适配android7.0）
     *
     * @param file
     * @return
     */
    public Uri getImageUri(File file) {
        Uri pictureUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
            pictureUri = mActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            pictureUri = Uri.fromFile(file);
        }
        return pictureUri;
    }

    /**
     * 安卓7.0裁剪根据文件路径获取uri
     */
    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = mActivity.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return mActivity.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param selected
     */
    public void compressImage(List<Uri> selected) {
        Observable.fromIterable(selected)
                .map(new Function<Uri, File>() {

                    @Override
                    public File apply(Uri uri) throws Exception {
                        try {
                            String path = FileUtils.getFilePath(_mActivity, uri);
                            if (path != null) {
                                File file = new File(path);
                                Logger.i("fileImageSize-Start-" + path + "Length=" + file.length());
                                return new Compressor(_mActivity).compressToFile(file);
                            }
                        } catch (Exception e) {
                        }
                        return null;
                    }
                })
                .filter(new Predicate<File>() {
                    @Override
                    public boolean test(File file) throws Exception {
                        if (file != null)
                            Logger.i("fileImageSize-End-" + file.getPath() + "Length=" + file.length());
                        return file != null;
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<File>>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        onLoading();
                    }

                    @Override
                    public void onSuccess(List<File> files) {
                        BaseUploadImageFragment.this.onSuccess();
                        upLoadMultipleFileImage(files);
                    }

                    @Override
                    public void onError(Throwable e) {
                        BaseUploadImageFragment.this.onSuccess();
                        Logger.e(e.toString());
                    }
                });
    }

    /**
     * 创建临时文件
     *
     * @param fileName
     * @return
     */
    public File createCacheFile(String fileName) {
        File rootFile = Environment.getExternalStorageDirectory();
        File fileAddress = new File(rootFile.getPath() + CACHE_FILE_PATH);
        if (!fileAddress.exists()) {
            boolean isMkdirs = fileAddress.mkdir();
            if (!isMkdirs) {
                Logger.e("创建文件夹失败");
                return null;
            }
        }
        return new File(fileAddress.getPath(), fileName);
    }

    /**
     * 压缩图片
     *
     * @param imageFile
     */
    public void compressImage(File imageFile) {
        Logger.i("FileImageSize-start:=" + imageFile.length());
        try {
            Observable.just(new Compressor(_mActivity).compressToFile(imageFile))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<File>() {
                        @Override
                        protected void onStart() {
                            super.onStart();
                            onLoading();
                        }

                        @Override
                        public void onNext(File file) {
                            Logger.i("FileImageSize-end:=" + file.length());
                            BaseUploadImageFragment.this.onSuccess();
                            upLoadImageFile(file);
                        }

                        @Override
                        public void onError(Throwable e) {
                            BaseUploadImageFragment.this.onSuccess();
                            Logger.e(e.toString());
                        }

                        @Override
                        public void onComplete() {
                            BaseUploadImageFragment.this.onSuccess();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
            Logger.e(e.toString());
        }

    }
}
