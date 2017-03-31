package com.tydic.android.usp.common;

import com.tydic.android.usp.common.AsyncTaskManager.*;


public class AsyncDownLoaderTask extends android.os.AsyncTask<String, Integer, Task> {

	/** synchronous Callback */
	private IResultCallback _resultCallback;

	/** ProgressCallback */
	private IProgressCallback _progressCallback;
	
	
	
	@Override
	protected Task doInBackground(String... params) {
		final Task result = new Task(params[0]);
		
		if (_progressCallback != null)
			_progressCallback.progressCallback(result);
		return result;
	}

	@Override
	protected void onPostExecute(Task result) {
		// After the implementation of the callback
		if (_resultCallback != null)
			_resultCallback.resultCallback(result);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	public final void execute(IProgressCallback proCallback, IResultCallback callback, String... params) {
		if(AsyncTaskManager.addTask(params[0]) == AsyncTaskManager.EXIST){
			return;
		}
		// 
		_progressCallback = proCallback;
		// 
		_resultCallback = callback;
		execute(params);
	}
	
}
