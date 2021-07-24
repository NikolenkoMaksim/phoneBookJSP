package ru.academits.nikolenko.service;

public class DeleteResults {
    private boolean isDeleted;
    private String error;

    public DeleteResults() {
        this.isDeleted = false;
        this.error = null;
    }

    public DeleteResults(boolean isDeleted, String error) {
        this.isDeleted = isDeleted;
        this.error = error;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
