package proj.me.bitframe.shading_three;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import proj.me.bitframe.BeanBitFrame;
import proj.me.bitframe.BeanImage;
import proj.me.bitframe.FrameModel;
import proj.me.bitframe.ImageCallback;
import proj.me.bitframe.ImageClickHandler;
import proj.me.bitframe.ImageType;
import proj.me.bitframe.R;
import proj.me.bitframe.databinding.ViewTrippleHorzBinding;
import proj.me.bitframe.databinding.ViewTrippleParrHorzBinding;
import proj.me.bitframe.databinding.ViewTrippleParrVertBinding;
import proj.me.bitframe.databinding.ViewTrippleVertBinding;
import proj.me.bitframe.dimentions.BeanShade3;
import proj.me.bitframe.dimentions.ShadeThree;
import proj.me.bitframe.helper.Utils;

/**
 * Created by Deepak.Tiwari on 29-09-2015.
 */
public class ImageShadingThree implements ImageClickHandler {
    LayoutInflater inflater;
    Context context;
    ImageCallback layoutCallback;
    int totalImages;

    String imageLink1, imageLink2, imageLink3;
    BeanBitFrame beanBitFrame1, beanBitFrame2, beanBitFrame3;

    BindingShadeThree bindingShadeThree;
    FrameModel frameModel;

    public ImageShadingThree(Context context, ImageCallback layoutCallback, int totalImages, FrameModel frameModel) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.layoutCallback = layoutCallback;
        this.totalImages = totalImages;

        beanBitFrame1 = new BeanBitFrame();
        beanBitFrame2 = new BeanBitFrame();
        beanBitFrame3 = new BeanBitFrame();
        beanBitFrame1.setLoaded(true);
        beanBitFrame2.setLoaded(true);
        beanBitFrame3.setLoaded(true);

        this.frameModel = frameModel;

    }

    public void updateTripleUi(List<Bitmap> images, List<BeanImage> beanImages, boolean hasImageProperties) {
        BeanBitFrame beanBitFrameFirst = null, beanBitFrameSecond = null, beanBitFrameThird = null;
        if(hasImageProperties){
            beanBitFrameFirst = (BeanBitFrame) beanImages.get(0);
            beanBitFrameSecond = (BeanBitFrame) beanImages.get(1);
            beanBitFrameThird = (BeanBitFrame) beanImages.get(2);
        }

        beanBitFrame1.setLocalImage(beanImages.get(0).isLocalImage());
        beanBitFrame1.setLocalImage(beanImages.get(0).isHasExtention());

        beanBitFrame2.setLocalImage(beanImages.get(1).isLocalImage());
        beanBitFrame2.setLocalImage(beanImages.get(1).isHasExtention());

        beanBitFrame3.setLocalImage(beanImages.get(2).isLocalImage());
        beanBitFrame3.setLocalImage(beanImages.get(2).isHasExtention());

        int width1 = hasImageProperties ? (int)beanBitFrameFirst.getWidth() : images.get(0).getWidth();
        int height1 = hasImageProperties ? (int)beanBitFrameFirst.getHeight() : images.get(0).getHeight();

        int width2 = hasImageProperties ? (int)beanBitFrameSecond.getWidth() : images.get(1).getWidth();
        int height2 = hasImageProperties ? (int)beanBitFrameSecond.getHeight() : images.get(1).getHeight();

        int width3 = hasImageProperties ? (int)beanBitFrameThird.getWidth() : images.get(2).getWidth();
        int height3 = hasImageProperties ? (int)beanBitFrameThird.getHeight() : images.get(2).getHeight();

        Log.e("MAX_WIDTH", ""+ frameModel.getMaxContainerWidth());
        Log.e("MAX_HEIGHT", ""+frameModel.getMaxContainerHeight());
        Log.e("MIN_WIDTH", ""+frameModel.getMinFrameWidth());
        Log.e("MIN_HIGHT", ""+frameModel.getMinFrameHeight());

        Log.e("getWidth1 : ", ""+width1);
        Log.e("getHeight1 : ", ""+height1);
        Log.e("getWidth2 : ", ""+width2);
        Log.e("getHeight2 : ", ""+height2);
        Log.e("getWidth3 : ", ""+width3);
        Log.e("getHeight3 : ", ""+height3+"\n\n");

        BeanShade3 beanShade3 = ShadeThree.calculateDimentions(frameModel, width1, height1, width2, height2, width3, height3);

        Log.e("Start", "++++++++++++++++++++++++++++++++++++Start");
        Log.e("getWidth1 : ", ""+beanShade3.getWidth1());
        Log.e("getHeight1 : ", ""+beanShade3.getHeight1());
        Log.e("getWidth2 : ", ""+beanShade3.getWidth2());
        Log.e("getHeight2 : ", ""+beanShade3.getHeight2());
        Log.e("getWidth3 : ", ""+beanShade3.getWidth3());
        Log.e("getHeight3 : ", ""+beanShade3.getHeight3());
        for(int i=0;i<3;i++) Log.e("image order : ", ""+beanShade3.getImageOrderList().get(i));

        Log.e("layoutType : ", ""+beanShade3.getLayoutType());
        Log.e("End", "++++++++++++++++++++++++++++++++++++End");

        imageLink1  = beanImages.get(0).getImageLink();
        imageLink2  = beanImages.get(1).getImageLink();
        imageLink3  = beanImages.get(2).getImageLink();

        boolean isFirstLocalImage = beanImages.get(0).isLocalImage();
        boolean isSecondLocalImage = beanImages.get(1).isLocalImage();
        boolean isThirdLocalImage = beanImages.get(2).isLocalImage();

        boolean isFirstHasExtension = beanImages.get(0).isHasExtention();
        boolean isSecondHasExtension = beanImages.get(1).isHasExtention();
        boolean isThirdHasExtension = beanImages.get(2).isHasExtention();

        int firstPrimaryCount = beanImages.get(0).getPrimaryCount();
        int firstSecondaryCount = beanImages.get(0).getSecondaryCount();

        int secondPrimaryCount = beanImages.get(1).getPrimaryCount();
        int secondSecondaryCount = beanImages.get(1).getSecondaryCount();

        int thirdPrimaryCount = beanImages.get(2).getPrimaryCount();
        int thirdSecondaryCount = beanImages.get(2).getSecondaryCount();

        bindingShadeThree = new BindingShadeThree();
        View root = null;

        switch(beanShade3.getLayoutType()){
            case PARALLEL_VERT:
                ViewTrippleParrVertBinding viewTrippleParrVertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_tripple_parr_vert, null));
                viewTrippleParrVertBinding.setClickHandler(this);
                viewTrippleParrVertBinding.setShadeThree(bindingShadeThree);
                root = viewTrippleParrVertBinding.getRoot();
                break;
            case PARALLEL_HORZ:
                ViewTrippleParrHorzBinding viewTrippleParrHorzBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_tripple_parr_horz, null));
                viewTrippleParrHorzBinding.setClickHandler(this);
                viewTrippleParrHorzBinding.setShadeThree(bindingShadeThree);
                root = viewTrippleParrHorzBinding.getRoot();
                break;
            case VERT:
                ViewTrippleVertBinding viewTrippleVertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_tripple_vert, null));
                viewTrippleVertBinding.setClickHandler(this);
                viewTrippleVertBinding.setShadeThree(bindingShadeThree);
                root = viewTrippleVertBinding.getRoot();
                break;
            case HORZ:
                ViewTrippleHorzBinding viewTrippleHorzBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_tripple_horz, null));
                viewTrippleHorzBinding.setClickHandler(this);
                viewTrippleHorzBinding.setShadeThree(bindingShadeThree);
                root = viewTrippleHorzBinding.getRoot();
                break;
        }

        Bitmap bitmap1 = null, bitmap2 = null, bitmap3 = null;

        int resultColor = 0;
        boolean hasGreaterVibrantPopulation = false;
        switch (beanShade3.getImageOrderList().get(0)){
            case FIRST:
                bitmap1 = hasImageProperties ? null : images.get(0);
                bindingShadeThree.setFirstComment(beanImages.get(0).getImageComment());
                imageLink1 = beanImages.get(0).getImageLink();
                isFirstLocalImage = beanImages.get(0).isLocalImage();
                isFirstHasExtension = beanImages.get(0).isHasExtention();
                firstPrimaryCount = beanImages.get(0).getPrimaryCount();
                firstSecondaryCount = beanImages.get(0).getSecondaryCount();

                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameFirst.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameFirst.getVibrantColor() : beanBitFrameFirst.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameFirst.getVibrantColor();
                            else resultColor = beanBitFrameFirst.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameFirst.getMutedColor();
                            else resultColor = beanBitFrameFirst.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setFirstImageBgColor(resultColor);
                    bindingShadeThree.setFirstCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame1.setMutedColor(beanBitFrameFirst.getMutedColor());
                    beanBitFrame1.setVibrantColor(beanBitFrameFirst.getVibrantColor());
                    beanBitFrame1.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame1.setPrimaryCount(beanBitFrameFirst.getPrimaryCount());
                    beanBitFrame1.setSecondaryCount(beanBitFrameFirst.getSecondaryCount());

                    beanBitFrame1.setWidth(/*beanShade3.getWidth1()*/beanBitFrameFirst.getWidth());
                    beanBitFrame1.setHeight(/*beanShade3.getHeight1()*/beanBitFrameFirst.getHeight());
                }

                break;
            case SECOND:
                bitmap1 = hasImageProperties ? null :images.get(1);
                bindingShadeThree.setFirstComment(beanImages.get(1).getImageComment());
                imageLink1 = beanImages.get(1).getImageLink();
                isFirstLocalImage = beanImages.get(1).isLocalImage();
                isFirstHasExtension = beanImages.get(1).isHasExtention();
                firstPrimaryCount = beanImages.get(1).getPrimaryCount();
                firstSecondaryCount = beanImages.get(1).getSecondaryCount();

                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameSecond.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameSecond.getVibrantColor() : beanBitFrameSecond.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameSecond.getVibrantColor();
                            else resultColor = beanBitFrameSecond.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameSecond.getMutedColor();
                            else resultColor = beanBitFrameSecond.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setFirstImageBgColor(resultColor);
                    bindingShadeThree.setFirstCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame1.setMutedColor(beanBitFrameSecond.getMutedColor());
                    beanBitFrame1.setVibrantColor(beanBitFrameSecond.getVibrantColor());
                    beanBitFrame1.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame1.setPrimaryCount(beanBitFrameSecond.getPrimaryCount());
                    beanBitFrame1.setSecondaryCount(beanBitFrameSecond.getSecondaryCount());

                    beanBitFrame1.setWidth(/*beanShade3.getWidth1()*/beanBitFrameSecond.getWidth());
                    beanBitFrame1.setHeight(/*beanShade3.getHeight1()*/beanBitFrameSecond.getHeight());
                }

                break;
            case THIRD:
                bitmap1 = hasImageProperties ? null : images.get(2);
                bindingShadeThree.setFirstComment(beanImages.get(2).getImageComment());
                imageLink1 = beanImages.get(2).getImageLink();
                isFirstLocalImage = beanImages.get(2).isLocalImage();
                isFirstHasExtension = beanImages.get(2).isHasExtention();
                firstPrimaryCount = beanImages.get(2).getPrimaryCount();
                firstSecondaryCount = beanImages.get(2).getSecondaryCount();

                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameThird.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameThird.getVibrantColor() : beanBitFrameThird.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameThird.getVibrantColor();
                            else resultColor = beanBitFrameThird.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameThird.getMutedColor();
                            else resultColor = beanBitFrameThird.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setFirstImageBgColor(resultColor);
                    bindingShadeThree.setFirstCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame1.setMutedColor(beanBitFrameThird.getMutedColor());
                    beanBitFrame1.setVibrantColor(beanBitFrameThird.getVibrantColor());
                    beanBitFrame1.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame1.setPrimaryCount(beanBitFrameThird.getPrimaryCount());
                    beanBitFrame1.setSecondaryCount(beanBitFrameThird.getSecondaryCount());

                    beanBitFrame1.setWidth(/*beanShade3.getWidth1()*/beanBitFrameThird.getWidth());
                    beanBitFrame1.setHeight(/*beanShade3.getHeight1()*/beanBitFrameThird.getHeight());
                }

                break;
        }switch (beanShade3.getImageOrderList().get(1)){
            case FIRST:
                bitmap2 = hasImageProperties ? null : images.get(0);
                bindingShadeThree.setSecondComment(beanImages.get(0).getImageComment());
                imageLink2 = beanImages.get(0).getImageLink();
                isSecondLocalImage = beanImages.get(0).isLocalImage();
                isSecondHasExtension = beanImages.get(0).isHasExtention();
                secondPrimaryCount = beanImages.get(0).getPrimaryCount();
                secondSecondaryCount = beanImages.get(0).getSecondaryCount();

                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameFirst.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameFirst.getVibrantColor() : beanBitFrameFirst.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameFirst.getVibrantColor();
                            else resultColor = beanBitFrameFirst.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameFirst.getMutedColor();
                            else resultColor = beanBitFrameFirst.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setSecondImageBgColor(resultColor);
                    bindingShadeThree.setSecondCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame2.setMutedColor(beanBitFrameFirst.getMutedColor());
                    beanBitFrame2.setVibrantColor(beanBitFrameFirst.getVibrantColor());
                    beanBitFrame2.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame2.setPrimaryCount(beanBitFrameFirst.getPrimaryCount());
                    beanBitFrame2.setSecondaryCount(beanBitFrameFirst.getSecondaryCount());

                    beanBitFrame2.setWidth(/*beanShade3.getWidth1()*/beanBitFrameFirst.getWidth());
                    beanBitFrame2.setHeight(/*beanShade3.getHeight1()*/beanBitFrameFirst.getHeight());
                }

                break;
            case SECOND:
                bitmap2 = hasImageProperties ? null : images.get(1);
                bindingShadeThree.setSecondComment(beanImages.get(1).getImageComment());
                imageLink2 = beanImages.get(1).getImageLink();
                isSecondLocalImage = beanImages.get(1).isLocalImage();
                isSecondHasExtension = beanImages.get(1).isHasExtention();
                secondPrimaryCount = beanImages.get(1).getPrimaryCount();
                secondSecondaryCount = beanImages.get(1).getSecondaryCount();

                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameSecond.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameSecond.getVibrantColor() : beanBitFrameSecond.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameSecond.getVibrantColor();
                            else resultColor = beanBitFrameSecond.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameSecond.getMutedColor();
                            else resultColor = beanBitFrameSecond.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setSecondImageBgColor(resultColor);
                    bindingShadeThree.setSecondCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame2.setMutedColor(beanBitFrameSecond.getMutedColor());
                    beanBitFrame2.setVibrantColor(beanBitFrameSecond.getVibrantColor());
                    beanBitFrame2.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame2.setPrimaryCount(beanBitFrameSecond.getPrimaryCount());
                    beanBitFrame2.setSecondaryCount(beanBitFrameSecond.getSecondaryCount());

                    beanBitFrame2.setWidth(/*beanShade3.getWidth1()*/beanBitFrameSecond.getWidth());
                    beanBitFrame2.setHeight(/*beanShade3.getHeight1()*/beanBitFrameSecond.getHeight());
                }

                break;
            case THIRD:
                bitmap2 = hasImageProperties ? null : images.get(2);
                bindingShadeThree.setSecondComment(beanImages.get(2).getImageComment());
                imageLink2 = beanImages.get(2).getImageLink();
                isSecondLocalImage = beanImages.get(2).isLocalImage();
                isSecondHasExtension = beanImages.get(2).isHasExtention();
                secondPrimaryCount = beanImages.get(2).getPrimaryCount();
                secondSecondaryCount = beanImages.get(2).getSecondaryCount();


                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameThird.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameThird.getVibrantColor() : beanBitFrameThird.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameThird.getVibrantColor();
                            else resultColor = beanBitFrameThird.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameThird.getMutedColor();
                            else resultColor = beanBitFrameThird.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setSecondImageBgColor(resultColor);
                    bindingShadeThree.setSecondCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame2.setMutedColor(beanBitFrameThird.getMutedColor());
                    beanBitFrame2.setVibrantColor(beanBitFrameThird.getVibrantColor());
                    beanBitFrame2.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame2.setPrimaryCount(beanBitFrameThird.getPrimaryCount());
                    beanBitFrame2.setSecondaryCount(beanBitFrameThird.getSecondaryCount());

                    beanBitFrame2.setWidth(/*beanShade3.getWidth1()*/beanBitFrameThird.getWidth());
                    beanBitFrame2.setHeight(/*beanShade3.getHeight1()*/beanBitFrameThird.getHeight());
                }

                break;
        }switch (beanShade3.getImageOrderList().get(2)){
            case FIRST:
                bitmap3 = hasImageProperties ? null : images.get(0);
                bindingShadeThree.setThirdComment(beanImages.get(0).getImageComment());
                imageLink3 = beanImages.get(0).getImageLink();
                isThirdLocalImage = beanImages.get(0).isLocalImage();
                isThirdHasExtension = beanImages.get(0).isHasExtention();
                thirdPrimaryCount = beanImages.get(0).getPrimaryCount();
                thirdSecondaryCount = beanImages.get(0).getSecondaryCount();


                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameFirst.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameFirst.getVibrantColor() : beanBitFrameFirst.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameFirst.getVibrantColor();
                            else resultColor = beanBitFrameFirst.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameFirst.getMutedColor();
                            else resultColor = beanBitFrameFirst.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setThirdImageBgColor(resultColor);
                    bindingShadeThree.setThirdCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame3.setMutedColor(beanBitFrameFirst.getMutedColor());
                    beanBitFrame3.setVibrantColor(beanBitFrameFirst.getVibrantColor());
                    beanBitFrame3.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame3.setPrimaryCount(beanBitFrameFirst.getPrimaryCount());
                    beanBitFrame3.setSecondaryCount(beanBitFrameFirst.getSecondaryCount());

                    beanBitFrame3.setWidth(/*beanShade3.getWidth1()*/beanBitFrameFirst.getWidth());
                    beanBitFrame3.setHeight(/*beanShade3.getHeight1()*/beanBitFrameFirst.getHeight());
                }

                break;
            case SECOND:
                bitmap3 = hasImageProperties ? null : images.get(1);
                bindingShadeThree.setThirdComment(beanImages.get(1).getImageComment());
                imageLink3 = beanImages.get(1).getImageLink();
                isThirdLocalImage = beanImages.get(1).isLocalImage();
                isThirdHasExtension = beanImages.get(1).isHasExtention();
                thirdPrimaryCount = beanImages.get(1).getPrimaryCount();
                thirdSecondaryCount = beanImages.get(1).getSecondaryCount();

                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameSecond.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameSecond.getVibrantColor() : beanBitFrameSecond.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameSecond.getVibrantColor();
                            else resultColor = beanBitFrameSecond.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameSecond.getMutedColor();
                            else resultColor = beanBitFrameSecond.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setThirdImageBgColor(resultColor);
                    bindingShadeThree.setThirdCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame3.setMutedColor(beanBitFrameSecond.getMutedColor());
                    beanBitFrame3.setVibrantColor(beanBitFrameSecond.getVibrantColor());
                    beanBitFrame3.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame3.setPrimaryCount(beanBitFrameSecond.getPrimaryCount());
                    beanBitFrame3.setSecondaryCount(beanBitFrameSecond.getSecondaryCount());

                    beanBitFrame3.setWidth(/*beanShade3.getWidth1()*/beanBitFrameSecond.getWidth());
                    beanBitFrame3.setHeight(/*beanShade3.getHeight1()*/beanBitFrameSecond.getHeight());
                }

                break;
            case THIRD:
                bitmap3 = hasImageProperties ? null : images.get(2);
                bindingShadeThree.setThirdComment(beanImages.get(2).getImageComment());
                imageLink3 = beanImages.get(2).getImageLink();
                isThirdLocalImage = beanImages.get(2).isLocalImage();
                isThirdHasExtension = beanImages.get(2).isHasExtention();
                thirdPrimaryCount = beanImages.get(2).getPrimaryCount();
                thirdSecondaryCount = beanImages.get(2).getSecondaryCount();

                if(hasImageProperties) {
                    hasGreaterVibrantPopulation = beanBitFrameThird.isHasGreaterVibrantPopulation();
                    resultColor = hasGreaterVibrantPopulation ? beanBitFrameThird.getVibrantColor() : beanBitFrameThird.getMutedColor();
                    switch (frameModel.getColorCombination()) {
                        case VIBRANT_TO_MUTED:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameThird.getVibrantColor();
                            else resultColor = beanBitFrameThird.getMutedColor();
                            break;
                        case MUTED_TO_VIBRANT:
                            if (hasGreaterVibrantPopulation)
                                resultColor = beanBitFrameThird.getMutedColor();
                            else resultColor = beanBitFrameThird.getVibrantColor();
                            break;
                    }

                    bindingShadeThree.setThirdImageBgColor(resultColor);
                    bindingShadeThree.setThirdCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));

                    beanBitFrame3.setMutedColor(beanBitFrameThird.getMutedColor());
                    beanBitFrame3.setVibrantColor(beanBitFrameThird.getVibrantColor());
                    beanBitFrame3.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);

                    beanBitFrame3.setPrimaryCount(beanBitFrameThird.getPrimaryCount());
                    beanBitFrame3.setSecondaryCount(beanBitFrameThird.getSecondaryCount());

                    beanBitFrame3.setWidth(/*beanShade3.getWidth1()*/beanBitFrameThird.getWidth());
                    beanBitFrame3.setHeight(/*beanShade3.getHeight1()*/beanBitFrameThird.getHeight());
                }

                break;
        }

        final ImageView imageView1 = (ImageView)root.findViewById(R.id.view_triple_image1);
        final ImageView imageView2 = (ImageView)root.findViewById(R.id.view_triple_image2);
        final ImageView imageView3 = (ImageView)root.findViewById(R.id.view_triple_image3);

        if(totalImages > 3) {
            bindingShadeThree.setCounterVisibility(true);
            bindingShadeThree.setCounterText("+" + (totalImages - 3));
        }

        BindingShadeThree.setLayoutWidth(imageView1, beanShade3.getWidth1());
        BindingShadeThree.setLayoutWidth(imageView2, beanShade3.getWidth2());
        BindingShadeThree.setLayoutWidth(imageView3, beanShade3.getWidth3());

        BindingShadeThree.setLayoutHeight(imageView1, beanShade3.getHeight1());
        BindingShadeThree.setLayoutHeight(imageView2, beanShade3.getHeight2());
        BindingShadeThree.setLayoutHeight(imageView3, beanShade3.getHeight3());

        bindingShadeThree.setFirstImageScaleType(frameModel.getScaleType());
        bindingShadeThree.setSecondImageScaleType(frameModel.getScaleType());
        bindingShadeThree.setThirdImageScaleType(frameModel.getScaleType());

        if(!hasImageProperties) {
            BindingShadeThree.setBitmap(imageView1, bitmap1);
            generatePalette(bitmap1, 0);
            BindingShadeThree.setBitmap(imageView2, bitmap2);
            generatePalette(bitmap2, 1);
            BindingShadeThree.setBitmap(imageView3, bitmap3);
            generatePalette(bitmap3, 2);
        }

        bindingShadeThree.setFirstCommentVisibility(frameModel.isShouldShowComment());
        bindingShadeThree.setSecondCommentVisibility(frameModel.isShouldShowComment());
        bindingShadeThree.setThirdCommentVisibility(frameModel.isShouldShowComment());

        //a/c to layout type
        switch (beanShade3.getLayoutType()){
            case PARALLEL_VERT:
                layoutCallback.addImageView(root, beanShade3.getWidth1(), beanShade3.getHeight1() + beanShade3.getHeight2() + beanShade3.getHeight3(), false);
                break;
            case PARALLEL_HORZ:
                layoutCallback.addImageView(root, beanShade3.getWidth1() + beanShade3.getWidth2() +  beanShade3.getWidth3(), beanShade3.getHeight1(), false);
                break;
            case VERT:
                layoutCallback.addImageView(root, beanShade3.getWidth1(), beanShade3.getHeight1() + beanShade3.getHeight2(), false);
                break;
            case HORZ:
                layoutCallback.addImageView(root, beanShade3.getWidth1() + beanShade3.getWidth2(), beanShade3.getHeight1(), false);
                break;
        }

        if(!hasImageProperties) {
            beanBitFrame1.setWidth(/*beanShade3.getWidth1()*/bitmap1.getWidth());
            beanBitFrame1.setHeight(/*beanShade3.getHeight1()*/bitmap1.getHeight());

            beanBitFrame2.setWidth(/*beanShade3.getWidth2()*/bitmap2.getWidth());
            beanBitFrame2.setHeight(/*beanShade3.getHeight2()*/bitmap2.getHeight());

            beanBitFrame3.setWidth(/*beanShade3.getWidth3()*/bitmap3.getWidth());
            beanBitFrame3.setHeight(/*beanShade3.getHeight3()*/bitmap3.getHeight());
        }

        beanBitFrame1.setImageLink(imageLink1);
        beanBitFrame1.setImageComment(bindingShadeThree.getFirstComment());
        beanBitFrame1.setLocalImage(isFirstLocalImage);
        beanBitFrame1.setHasExtention(isFirstHasExtension);
        beanBitFrame1.setPrimaryCount(firstPrimaryCount);
        beanBitFrame1.setSecondaryCount(firstSecondaryCount);

        beanBitFrame2.setImageLink(imageLink2);
        beanBitFrame2.setImageComment(bindingShadeThree.getSecondComment());
        beanBitFrame2.setLocalImage(isSecondLocalImage);
        beanBitFrame2.setHasExtention(isSecondHasExtension);
        beanBitFrame2.setPrimaryCount(secondPrimaryCount);
        beanBitFrame2.setSecondaryCount(secondSecondaryCount);

        beanBitFrame3.setImageLink(imageLink3);
        beanBitFrame3.setImageComment(bindingShadeThree.getThirdComment());
        beanBitFrame3.setLocalImage(isThirdLocalImage);
        beanBitFrame3.setHasExtention(isThirdHasExtension);
        beanBitFrame3.setPrimaryCount(thirdPrimaryCount);
        beanBitFrame3.setSecondaryCount(thirdSecondaryCount);

        if(hasImageProperties){
            int mixedColor = Utils.getMixedArgbColor(bindingShadeThree.getFirstImageBgColor(), bindingShadeThree.getSecondImageBgColor(), bindingShadeThree.getThirdImageBgColor());
            int inverseColor = Utils.getInverseColor(mixedColor);

            layoutCallback.setColorsToAddMoreView(bindingShadeThree.getThirdImageBgColor(), mixedColor, inverseColor);
            layoutCallback.frameResult(beanBitFrame1, beanBitFrame2, beanBitFrame3);

            //bindingShadeThree.setDividerVisible(Utils.showShowDivider());
            bindingShadeThree.setDividerColor(inverseColor);

            final Picasso picasso = Picasso.with(context.getApplicationContext());
            //need to notify ImageShading too, to load image via picasso
            Utils.logError("IMAGE_LOADING : "+" going to load three image");
            if(frameModel.isShouldStoreImages()){
                picasso.load(imageLink1).fit().centerInside().noPlaceholder().into(imageView1, new Callback() {
                    @Override
                    public void onSuccess() {
                        //do nothing
                        Utils.logError("IMAGE_LOADING success");
                    }

                    @Override
                    public void onError() {
                        Utils.logError("IMAGE_LOADING error");
                        picasso.load(imageLink1+"?"+System.currentTimeMillis()).fit().centerInside().noPlaceholder().into(imageView1);
                    }
                });
                picasso.load(imageLink2).fit().centerInside().noPlaceholder().into(imageView2, new Callback() {
                    @Override
                    public void onSuccess() {
                        //do nothing
                        Utils.logError("IMAGE_LOADING success");
                    }

                    @Override
                    public void onError() {
                        Utils.logError("IMAGE_LOADING error");
                        picasso.load(imageLink2+"?"+System.currentTimeMillis()).fit().centerInside().noPlaceholder().into(imageView2);
                    }
                });
                picasso.load(imageLink3).fit().centerInside().noPlaceholder().into(imageView3, new Callback() {
                    @Override
                    public void onSuccess() {
                        //do nothing
                        Utils.logError("IMAGE_LOADING success");
                    }

                    @Override
                    public void onError() {
                        Utils.logError("IMAGE_LOADING error");
                        picasso.load(imageLink3+"?"+System.currentTimeMillis()).fit().centerInside().noPlaceholder().into(imageView3);
                    }
                });
            }else {
                picasso.load(imageLink1).memoryPolicy(MemoryPolicy.NO_STORE)
                        .networkPolicy(NetworkPolicy.NO_STORE).fit().centerInside().noPlaceholder().into(imageView1, new Callback() {
                    @Override
                    public void onSuccess() {
                        //do nothing
                        Utils.logError("IMAGE_LOADING success");
                    }

                    @Override
                    public void onError() {
                        Utils.logError("IMAGE_LOADING error");
                        picasso.load(imageLink1+"?"+System.currentTimeMillis()).memoryPolicy(MemoryPolicy.NO_STORE)
                                .networkPolicy(NetworkPolicy.NO_STORE).fit().centerInside().noPlaceholder().into(imageView1);
                    }
                });
                picasso.load(imageLink2).memoryPolicy(MemoryPolicy.NO_STORE)
                        .networkPolicy(NetworkPolicy.NO_STORE).fit().centerInside().noPlaceholder().into(imageView2, new Callback() {
                    @Override
                    public void onSuccess() {
                        //do nothing
                        Utils.logError("IMAGE_LOADING success");
                    }

                    @Override
                    public void onError() {
                        Utils.logError("IMAGE_LOADING error");
                        picasso.load(imageLink2+"?"+System.currentTimeMillis()).memoryPolicy(MemoryPolicy.NO_STORE)
                                .networkPolicy(NetworkPolicy.NO_STORE).fit().centerInside().noPlaceholder().into(imageView2);
                    }
                });
                picasso.load(imageLink3).memoryPolicy(MemoryPolicy.NO_STORE)
                        .networkPolicy(NetworkPolicy.NO_STORE).fit().centerInside().noPlaceholder().into(imageView3, new Callback() {
                    @Override
                    public void onSuccess() {
                        //do nothing
                        Utils.logError("IMAGE_LOADING success");
                    }

                    @Override
                    public void onError() {
                        Utils.logError("IMAGE_LOADING error");
                        picasso.load(imageLink3+"?"+System.currentTimeMillis()).memoryPolicy(MemoryPolicy.NO_STORE)
                                .networkPolicy(NetworkPolicy.NO_STORE).fit().centerInside().noPlaceholder().into(imageView3);
                    }
                });
            }
        }
    }

    @Override
    public void onImageShadeClick(View view) {
        switch((String)view.getTag()){
            case "img1":
                layoutCallback.imageClicked(ImageType.VIEW_TRIPLE_1, 1, imageLink1);
                break;
            case "img2":
                layoutCallback.imageClicked(ImageType.VIEW_TRIPLE_1, 2, imageLink2);
                break;
            case "img3":
                layoutCallback.imageClicked(ImageType.VIEW_TRIPLE_1, 3, imageLink3);
                break;
        }
    }

    private int[] resultColor = new int[3];

    private void generatePalette(Bitmap bitmap, final int viewId){
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int defaultColor = Color.parseColor("#ffffffff");
                int resultColor = 0;
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                int vibrantPopulation = vibrantSwatch == null ? 0 : vibrantSwatch.getPopulation();
                int mutedPopulation = mutedSwatch == null ? 0 : mutedSwatch.getPopulation();


                int vibrantColor = palette.getVibrantColor(defaultColor);
                int mutedColor = palette.getMutedColor(defaultColor);
                boolean hasGreaterVibrantPopulation = vibrantPopulation > mutedPopulation;
                switch(frameModel.getColorCombination()){
                    case VIBRANT_TO_MUTED:
                        if(hasGreaterVibrantPopulation)
                            resultColor = vibrantColor;
                        else resultColor = mutedColor;
                        break;
                    case MUTED_TO_VIBRANT:
                        if(hasGreaterVibrantPopulation)
                            resultColor = mutedColor;
                        else resultColor = vibrantColor;
                        break;
                }

                Utils.logMessage("vibrant pop = "+vibrantPopulation+"  muted pop"+mutedPopulation);

                switch(viewId){
                    case 0:
                        bindingShadeThree.setFirstImageBgColor(resultColor);
                        bindingShadeThree.setFirstCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));
                        beanBitFrame1.setMutedColor(mutedColor);
                        beanBitFrame1.setVibrantColor(vibrantColor);
                        beanBitFrame1.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);
                        break;
                    case 1:
                        bindingShadeThree.setSecondImageBgColor(resultColor);
                        bindingShadeThree.setSecondCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));
                        beanBitFrame2.setMutedColor(mutedColor);
                        beanBitFrame2.setVibrantColor(vibrantColor);
                        beanBitFrame2.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);
                        break;
                    case 2:
                        bindingShadeThree.setThirdImageBgColor(resultColor);
                        bindingShadeThree.setThirdCommentBgColor(Utils.getColorWithTransparency(resultColor, frameModel.getCommentTransparencyPercent()));
                        beanBitFrame3.setMutedColor(mutedColor);
                        beanBitFrame3.setVibrantColor(vibrantColor);
                        beanBitFrame3.setHasGreaterVibrantPopulation(hasGreaterVibrantPopulation);
                        break;
                }
                ImageShadingThree.this.resultColor[viewId] = resultColor;

                if(ImageShadingThree.this.resultColor[0] == 0 || ImageShadingThree.this.resultColor[1] == 0 ||ImageShadingThree.this.resultColor[2] == 0) return;

                int mixedColor = Utils.getMixedArgbColor(ImageShadingThree.this.resultColor);
                int inverseColor = Utils.getInverseColor(mixedColor);
                layoutCallback.setColorsToAddMoreView(resultColor, mixedColor, inverseColor);
                layoutCallback.frameResult(beanBitFrame1, beanBitFrame2, beanBitFrame3);

                //bindingShadeThree.setDividerVisible(Utils.showShowDivider());
                bindingShadeThree.setDividerColor(inverseColor);
            }
        });
    }
}
