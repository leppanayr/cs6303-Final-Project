package edu.utdallas.cs6303.finalproject.model.device;

import edu.utdallas.cs6303.finalproject.services.storage.enums.StorageServiceSizeEnum;

public class DeviceInfo {
    private boolean supportsWebP;
    private String imgFileSizePath;
    public DeviceInfo (String userAgent) {
        this.supportsWebP = deviceSupportsWebP(userAgent);
        this.imgFileSizePath = getValueForUserAgent(userAgent);
    }

    public boolean getSupportsWebP() {
        return this.supportsWebP;
    }

    public String getImgFileSizePath() {
        return this.imgFileSizePath;
    }

    private boolean deviceSupportsWebP(String userAgent) {
        return (!userAgent.contains("iPhone") && !userAgent.contains("iPad") && !userAgent.equals("TinyMCE"));
    }

    private String getValueForUserAgent(String userAgent) {
        if (userAgent.contains("iPhone") || userAgent.contains("iPad") || userAgent.contains("Android")) {
            return capitalize(StorageServiceSizeEnum.MEDIUM);
        } else {
            return capitalize(StorageServiceSizeEnum.LARGE);
        }
    }

    public static String capitalize(StorageServiceSizeEnum size) {
        String lowerCaseString = size.toString().toLowerCase();
        return lowerCaseString.substring(0,1).toUpperCase() + lowerCaseString.substring(1);
    }
}