package com.hzp.lib_library.log;

public interface HiLogFormatter<T> {

    String format(T data);
}