package com.example.fullscreen.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.support.v7.app.ActionBarActivity;

/**
 * An API 11+ implementation of {@link SystemUiHider}. Uses APIs available in
 * Honeycomb and later (specifically {@link View#setSystemUiVisibility(int)}) to
 * show and hide the system UI.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class SystemUiHiderKitkat extends SystemUiHiderBase
{
	/**
	 * Flags for {@link View#setSystemUiVisibility(int)} to use when showing the
	 * system UI and hide system UI.
	 */
	private int mToggleFlags;

	/**
	 * Whether or not the system UI is currently visible. This is cached from
	 * {@link android.view.View.OnSystemUiVisibilityChangeListener}.
	 */
	private boolean mVisible = true;

	/**
	 * Constructor not intended to be called by clients. Use
	 * {@link SystemUiHider#getInstance} to obtain an instance.
	 */
	protected SystemUiHiderKitkat( ActionBarActivity activity, View anchorView,
																int flags )
	{
		super( activity, anchorView, flags );

		mToggleFlags = View.SYSTEM_UI_FLAG_FULLSCREEN
			| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
			| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
	}

	/** {@inheritDoc} */
	@Override
	public void setup( )
	{
		// It isn't need anymore the onSystemUiVisibilityChange listener
		// because in system immersive mode didn't have visibility changes
		// when immersive flags are seted or unseted.,
	}

	/** {@inheritDoc} */
	@Override
	public void hide( )
	{
		uiOptions = mActivity.getWindow( ).getDecorView( ).getSystemUiVisibility( );
		uiOptions |= mToggleFlags;
		mAnchorView.setSystemUiVisibility( uiOptions );
		// Visibility changes need to do out of onSystemUiVisibilityChange
		// because in system immersive mode didn't have visibility changes
		// when immersive flags are seted or unseted.
		mActivity.getSupportActionBar( ).hide( );
		// Trigger the registered listener and cache the visibility
		// state.
		mOnVisibilityChangeListener.onVisibilityChange( false );
		mVisible = false;
	}
	
	/** {@inheritDoc} */
	@Override
	public void show( )
	{
		uiOptions = mActivity.getWindow( ).getDecorView( ).getSystemUiVisibility( );
		uiOptions &= ~mToggleFlags;
		mAnchorView.setSystemUiVisibility( uiOptions );
		// Visibility changes need to do out of onSystemUiVisibilityChange
		// because in system immersive mode didn't have visibility changes
		// when immersive flags are seted or unseted.
		mActivity.getSupportActionBar( ).show( ); 
		// Trigger the registered listener and cache the visibility
		// state.
		mOnVisibilityChangeListener.onVisibilityChange( true );
		mVisible = true;

	}

	/** {@inheritDoc} */
	@Override
	public boolean isVisible( )
	{
		return mVisible;
	}
}
