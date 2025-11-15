package com.example.demo.Enum;

public enum FileExportError {
        UPLOAD_FAILED("文件上传失败"),
        ALREADY_EXIST("已存在"),
        FILE_NOT_EXIST("文件不存在"),
        FILE_TYPE_ERROR("文件类型错误"),
        FILE_TYPE_NOT_SUPPORTED("不支持此类型文件处理"),
        FILE_READ_ERROR("文件读取错误"),
        FILE_NAME_NOT_NULL("文件名不能为空");

        private final String message;

        FileExportError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return message;
        }

}
