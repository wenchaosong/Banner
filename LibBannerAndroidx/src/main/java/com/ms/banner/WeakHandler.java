package com.ms.banner;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

public class WeakHandler {

    private final Handler.Callback mCallback; // hard reference to Callback. We need to keep callback in memory
    private final ExecHandler mExec;
    private Lock mLock = new ReentrantLock();
    @SuppressWarnings("ConstantConditions")
    @VisibleForTesting
    final ChainedRef mRunnables = new ChainedRef(mLock, null);

    public WeakHandler() {
        mCallback = null;
        mExec = new ExecHandler();
    }

    public WeakHandler(@Nullable Handler.Callback callback) {
        mCallback = callback; // Hard referencing body
        mExec = new ExecHandler(new WeakReference<>(callback)); // Weak referencing inside ExecHandler
    }

    public WeakHandler(@NonNull Looper looper) {
        mCallback = null;
        mExec = new ExecHandler(looper);
    }

    public WeakHandler(@NonNull Looper looper, @NonNull Handler.Callback callback) {
        mCallback = callback;
        mExec = new ExecHandler(looper, new WeakReference<>(callback));
    }

    public final boolean post(@NonNull Runnable r) {
        return mExec.post(wrapRunnable(r));
    }

    public final boolean postAtTime(@NonNull Runnable r, long uptimeMillis) {
        return mExec.postAtTime(wrapRunnable(r), uptimeMillis);
    }

    public final boolean postAtTime(Runnable r, Object token, long uptimeMillis) {
        return mExec.postAtTime(wrapRunnable(r), token, uptimeMillis);
    }

    public final boolean postDelayed(Runnable r, long delayMillis) {
        return mExec.postDelayed(wrapRunnable(r), delayMillis);
    }

    public final boolean postAtFrontOfQueue(Runnable r) {
        return mExec.postAtFrontOfQueue(wrapRunnable(r));
    }

    /**
     * Remove any pending posts of Runnable r that are in the message queue.
     */
    public final void removeCallbacks(Runnable r) {
        final WeakRunnable runnable = mRunnables.remove(r);
        if (runnable != null) {
            mExec.removeCallbacks(runnable);
        }
    }

    /**
     * Remove any pending posts of Runnable <var>r</var> with Object
     * <var>token</var> that are in the message queue.  If <var>token</var> is null,
     * all callbacks will be removed.
     */
    public final void removeCallbacks(Runnable r, Object token) {
        final WeakRunnable runnable = mRunnables.remove(r);
        if (runnable != null) {
            mExec.removeCallbacks(runnable, token);
        }
    }

    /**
     * Pushes a message onto the end of the message queue after all pending messages
     * before the current time. It will be received in callback,
     * in the thread attached to this handler.
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    public final boolean sendMessage(Message msg) {
        return mExec.sendMessage(msg);
    }

    /**
     * Sends a Message containing only the what value.
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    public final boolean sendEmptyMessage(int what) {
        return mExec.sendEmptyMessage(what);
    }

    public final boolean sendEmptyMessageDelayed(int what, long delayMillis) {
        return mExec.sendEmptyMessageDelayed(what, delayMillis);
    }

    public final boolean sendEmptyMessageAtTime(int what, long uptimeMillis) {
        return mExec.sendEmptyMessageAtTime(what, uptimeMillis);
    }

    /**
     * Enqueue a message into the message queue after all pending messages
     * before (current time + delayMillis). You will receive it in
     * callback, in the thread attached to this handler.
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.  Note that a
     * result of true does not mean the message will be processed -- if
     * the looper is quit before the delivery time of the message
     * occurs then the message will be dropped.
     */
    public final boolean sendMessageDelayed(Message msg, long delayMillis) {
        return mExec.sendMessageDelayed(msg, delayMillis);
    }

    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        return mExec.sendMessageAtTime(msg, uptimeMillis);
    }

    /**
     * Enqueue a message at the front of the message queue, to be processed on
     * the next iteration of the message loop.  You will receive it in
     * callback, in the thread attached to this handler.
     * <b>This method is only for use in very special circumstances -- it
     * can easily starve the message queue, cause ordering problems, or have
     * other unexpected side-effects.</b>
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    public final boolean sendMessageAtFrontOfQueue(Message msg) {
        return mExec.sendMessageAtFrontOfQueue(msg);
    }

    /**
     * Remove any pending posts of messages with code 'what' that are in the
     * message queue.
     */
    public final void removeMessages(int what) {
        mExec.removeMessages(what);
    }

    /**
     * Remove any pending posts of messages with code 'what' and whose obj is
     * 'object' that are in the message queue.  If <var>object</var> is null,
     * all messages will be removed.
     */
    public final void removeMessages(int what, Object object) {
        mExec.removeMessages(what, object);
    }

    /**
     * Remove any pending posts of callbacks and sent messages whose
     * <var>obj</var> is <var>token</var>.  If <var>token</var> is null,
     * all callbacks and messages will be removed.
     */
    public final void removeCallbacksAndMessages(Object token) {
        mExec.removeCallbacksAndMessages(token);
    }

    /**
     * Check if there are any pending posts of messages with code 'what' in
     * the message queue.
     */
    public final boolean hasMessages(int what) {
        return mExec.hasMessages(what);
    }

    /**
     * Check if there are any pending posts of messages with code 'what' and
     * whose obj is 'object' in the message queue.
     */
    public final boolean hasMessages(int what, Object object) {
        return mExec.hasMessages(what, object);
    }

    public final Looper getLooper() {
        return mExec.getLooper();
    }

    private WeakRunnable wrapRunnable(@NonNull Runnable r) {
        //noinspection ConstantConditions
        if (r == null) {
            throw new NullPointerException("Runnable can't be null");
        }
        final ChainedRef hardRef = new ChainedRef(mLock, r);
        mRunnables.insertAfter(hardRef);
        return hardRef.wrapper;
    }

    private static class ExecHandler extends Handler {
        private final WeakReference<Callback> mCallback;

        ExecHandler() {
            mCallback = null;
        }

        ExecHandler(WeakReference<Callback> callback) {
            mCallback = callback;
        }

        ExecHandler(Looper looper) {
            super(looper);
            mCallback = null;
        }

        ExecHandler(Looper looper, WeakReference<Callback> callback) {
            super(looper);
            mCallback = callback;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (mCallback == null) {
                return;
            }
            final Callback callback = mCallback.get();
            if (callback == null) { // Already disposed
                return;
            }
            callback.handleMessage(msg);
        }
    }

    static class WeakRunnable implements Runnable {
        private final WeakReference<Runnable> mDelegate;
        private final WeakReference<ChainedRef> mReference;

        WeakRunnable(WeakReference<Runnable> delegate, WeakReference<ChainedRef> reference) {
            mDelegate = delegate;
            mReference = reference;
        }

        @Override
        public void run() {
            final Runnable delegate = mDelegate.get();
            final ChainedRef reference = mReference.get();
            if (reference != null) {
                reference.remove();
            }
            if (delegate != null) {
                delegate.run();
            }
        }
    }

    static class ChainedRef {
        @Nullable
        ChainedRef next;
        @Nullable
        ChainedRef prev;
        @NonNull
        final Runnable runnable;
        @NonNull
        final WeakRunnable wrapper;

        @NonNull
        Lock lock;

        public ChainedRef(@NonNull Lock lock, @NonNull Runnable r) {
            this.runnable = r;
            this.lock = lock;
            this.wrapper = new WeakRunnable(new WeakReference<>(r), new WeakReference<>(this));
        }

        public WeakRunnable remove() {
            lock.lock();
            try {
                if (prev != null) {
                    prev.next = next;
                }
                if (next != null) {
                    next.prev = prev;
                }
                prev = null;
                next = null;
            } finally {
                lock.unlock();
            }
            return wrapper;
        }

        public void insertAfter(@NonNull ChainedRef candidate) {
            lock.lock();
            try {
                if (this.next != null) {
                    this.next.prev = candidate;
                }

                candidate.next = this.next;
                this.next = candidate;
                candidate.prev = this;
            } finally {
                lock.unlock();
            }
        }

        @Nullable
        public WeakRunnable remove(Runnable obj) {
            lock.lock();
            try {
                ChainedRef curr = this.next; // Skipping head
                while (curr != null) {
                    if (curr.runnable == obj) { // We do comparison exactly how Handler does inside
                        return curr.remove();
                    }
                    curr = curr.next;
                }
            } finally {
                lock.unlock();
            }
            return null;
        }
    }
}