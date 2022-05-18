package com.spiritlight.rainstorm;

public enum RainStorm {
    INSTANCE;
    private boolean enabled = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean b) {
        enabled = b;
    }
}
