package com.czy.binderstudy;

// 定义一个接口 且继承 android.os.IInterface
public interface IMyAidlInterface extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
     * 基本Binder 类, 同时还实现 IMyAidlInterface接口, 该类是一个 abstract类, IMyAidlInterface 对应的方法需要子类去实现
     * 这个类 在服务端中 生产
     * Service onBinder 返回的一般就是 这个 Stub 的实现对象
     */
    public static abstract class Stub extends android.os.Binder implements com.czy.binderstudy.IMyAidlInterface {
        // 这个是 binder的 标识
        private static final java.lang.String DESCRIPTOR = "com.czy.binderstudy.IMyAidlInterface";

        /**
         * Construct the stub at attach it to the interface.
         * 构造方法 且吧 该对象 和 DESCRIPTOR 绑定在一起, 即如果在同一进程的话, 调用queryLocalInterface 方法返回不为空
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.czy.binderstudy.IMyAidlInterface interface,
         * generating a proxy if needed.
         * <p/>
         * 吧 Binder 转换层对应 接口实现对象
         *
         * @param obj binder 对象
         */
        public static com.czy.binderstudy.IMyAidlInterface asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            // 更具 表示在本进程寻找是否已经存在对应接口实现对象, 如果在则直接返回的
            // 这里的判断 其实是 为了 判断客户端 和 服务端是否是在统一进程.
            // 如果在同一进程的话, 一定可以找到对应的接口实现对象, 就不需要构建Proxy 对象实现跨进程调用了
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof com.czy.binderstudy.IMyAidlInterface))) {
                // 存在 强转返回
                return ((com.czy.binderstudy.IMyAidlInterface) iin);
            }
            // 不存在则 new 一个新的代理对象
            return new com.czy.binderstudy.IMyAidlInterface.Stub.Proxy(obj);
        }

        /**
         * @return 该类本身就是一个 binder 这里返回自己
         */
        @Override
        public android.os.IBinder asBinder() {
            return this;
        }


        /**
         * 这里 调用服务端的主要 方法
         *
         * @param code  对应的方法代码
         * @param data  对应的参数数据
         * @param reply 返回值数据
         * @param flags
         * @return
         * @throws android.os.RemoteException
         */
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    // 这里直接返回 binder 的表示
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_basicTypes: {
                    // 更具表示, 来确定 参数解析的数据类型  调用的是 native 方法
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    long _arg1;
                    _arg1 = data.readLong();
                    boolean _arg2;
                    _arg2 = (0 != data.readInt());
                    float _arg3;
                    _arg3 = data.readFloat();
                    double _arg4;
                    _arg4 = data.readDouble();
                    java.lang.String _arg5;
                    _arg5 = data.readString();
                    //上面是解析参数
                    // 执行 具体的方法
                    this.basicTypes(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPid: {
                    data.enforceInterface(DESCRIPTOR);
                    int _result = this.getPid();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_addBook: {
                    data.enforceInterface(DESCRIPTOR);
                    com.czy.binderstudy.Book _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = com.czy.binderstudy.Book.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    this.addBook(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getBook: {
                    data.enforceInterface(DESCRIPTOR);
                    com.czy.binderstudy.Book _result = this.getBook();
                    reply.writeNoException();
                    if ((_result != null)) {
                        // 这里 返回方法的 返回值
                        reply.writeInt(1);
                        _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        //私有内部类 且自动帮我们实现了我们定义的 AIDL 接口方法
        private static class Proxy implements com.czy.binderstudy.IMyAidlInterface {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            // 获取binder 表示
            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * 接口的实现方法
             */
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeLong(aLong);
                    _data.writeInt(((aBoolean) ? (1) : (0)));
                    _data.writeFloat(aFloat);
                    _data.writeDouble(aDouble);
                    _data.writeString(aString);
                    // 把参数序列化 成 Parcel 对象
                    // 调用远程 binder 的 transact 方法
                    mRemote.transact(Stub.TRANSACTION_basicTypes, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public int getPid() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getPid, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public void addBook(com.czy.binderstudy.Book book) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((book != null)) {
                        _data.writeInt(1);
                        book.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public com.czy.binderstudy.Book getBook() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                com.czy.binderstudy.Book _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getBook, _data, _reply, 0);
                    _reply.readException();
                    if ((0 != _reply.readInt())) {
                        _result = com.czy.binderstudy.Book.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        // 接口方法 对应的 id, 在onTransact 方法更具这些id 来确定到底实现哪个方法
        static final int TRANSACTION_basicTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
        static final int TRANSACTION_getBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    }

    // 下面4个方法 就是我们在 AIDL 文件中定义的接口方法
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException;

    public int getPid() throws android.os.RemoteException;

    public void addBook(com.czy.binderstudy.Book book) throws android.os.RemoteException;

    public com.czy.binderstudy.Book getBook() throws android.os.RemoteException;
}
