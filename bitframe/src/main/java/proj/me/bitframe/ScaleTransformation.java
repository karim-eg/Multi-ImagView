package proj.me.bitframe;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.squareup.picasso.Transformation;

import proj.me.bitframe.helper.Utils;

/**
 * Created by root on 15/9/16.
 */

public class ScaleTransformation implements Transformation {
    float containerWidth;
    float containerHeight;
    int totalImage;
    String key;
    BeanImage beanImage;
    ImageResult imageResult;

    ScaleTransformation(float containerWidth, float containerHeight, int totalImage, String key, BeanImage beanImage, ImageResult imageResult){
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.totalImage = totalImage;
        this.key = key;
        this.beanImage = beanImage;
        this.imageResult = imageResult;
    }
    @Override
    public Bitmap transform(Bitmap source) {
        totalImage = totalImage > 4 ? 4 : totalImage;
        Utils.logError("transforming"+source.getWidth()+" h"+source.getHeight());
        Bitmap target =  Utils.getScaledBitmap(source,
                (int)(containerWidth/(totalImage > 1 ? totalImage - 0.4f : 1)),
                (int)(containerHeight/(totalImage > 1 ? totalImage - 0.4f : 1)));
        boolean isNotEqual = false;
        if(isNotEqual = target != source) source.recycle();

        if(isNotEqual) imageResult.handleTransformedResult(target, beanImage);

        return isNotEqual ? Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565) : target;
    }



    @Override
    public String key() {
        return "square";
    }
}
