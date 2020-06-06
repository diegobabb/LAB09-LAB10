package com.lab09_lab10.ui.estEdiGua;

import android.support.annotation.Nullable;

class EstEdiGuaFormState {

    @Nullable
    private Integer cedulaError;
    @Nullable
    private Integer nombreError;
    @Nullable
    private Integer apellidosError;
    @Nullable
    private Integer edadError;
    @Nullable
    private Integer cursosError;

    private boolean isDataValid;

    EstEdiGuaFormState() {
        this.cedulaError = null;
        this.nombreError = null;
        this.apellidosError = null;
        this.edadError = null;
        this.cursosError = null;
        this.isDataValid = false;
    }

    @Nullable
    public Integer getCedulaError() {
        return cedulaError;
    }

    public void setCedulaError(@Nullable Integer cedulaError) {
        this.cedulaError = cedulaError;
    }

    @Nullable
    public Integer getNombreError() {
        return nombreError;
    }

    public void setNombreError(@Nullable Integer nombreError) {
        this.nombreError = nombreError;
    }

    @Nullable
    public Integer getApellidosError() {
        return apellidosError;
    }

    public void setApellidosError(@Nullable Integer apellidosError) {
        this.apellidosError = apellidosError;
    }

    @Nullable
    public Integer getEdadError() {
        return edadError;
    }

    public void setEdadError(@Nullable Integer edadError) {
        this.edadError = edadError;
    }

    @Nullable
    public Integer getCursosError() {
        return cursosError;
    }

    public void setCursosError(@Nullable Integer cursosError) {
        this.cursosError = cursosError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }

    public void setDataValid(boolean dataValid) {
        isDataValid = dataValid;
    }
}

