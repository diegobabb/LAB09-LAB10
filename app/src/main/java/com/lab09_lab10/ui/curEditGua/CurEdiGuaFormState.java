package com.lab09_lab10.ui.curEditGua;

import android.support.annotation.Nullable;

final public class CurEdiGuaFormState {
    @Nullable
    private Integer codigoError;
    @Nullable
    private Integer descripcionError;
    @Nullable
    private Integer creditosError;

    private boolean isDataValid;

    public CurEdiGuaFormState() {
        this.codigoError = null;
        this.descripcionError = null;
        this.creditosError = null;
        this.isDataValid = false;
    }

    @Nullable
    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(@Nullable Integer codigoError) {
        this.codigoError = codigoError;
    }

    @Nullable
    public Integer getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(@Nullable Integer descripcionError) {
        this.descripcionError = descripcionError;
    }

    @Nullable
    public Integer getCreditosError() {
        return creditosError;
    }

    public void setCreditosError(@Nullable Integer creditosError) {
        this.creditosError = creditosError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }

    public void setDataValid(boolean dataValid) {
        isDataValid = dataValid;
    }
}
