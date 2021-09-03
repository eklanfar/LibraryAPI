package com.eklanfar.library.retrofit.microblink.model.response;

import lombok.Data;

@Data
public class ImageAnalysisResult {

    private boolean blurred;
    private String documentImageColorStatus;
    private String documentImageMoireStatus;
    private String faceDetectionStatus;
    private String mrzDetectionStatus;
    private String barcodeDetectionStatus;
}
