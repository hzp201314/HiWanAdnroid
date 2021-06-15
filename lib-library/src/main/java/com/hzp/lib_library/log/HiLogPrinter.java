package com.hzp.lib_library.log;

import androidx.annotation.NonNull;

public interface HiLogPrinter {
    void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString);
}
