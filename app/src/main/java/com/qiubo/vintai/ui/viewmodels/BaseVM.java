package com.qiubo.vintai.ui.viewmodels;

public class BaseVM {
    private int mType;
    private int mVmId;
    private int mResourceId;

    public BaseVM(int type, int vmId, int resourceId) {
        mType = type;
        mVmId = vmId;
        mResourceId = resourceId;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getVmId() {
        return mVmId;
    }

    public void setVmId(int vmId) {
        mVmId = vmId;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int resourceId) {
        mResourceId = resourceId;
    }
}
