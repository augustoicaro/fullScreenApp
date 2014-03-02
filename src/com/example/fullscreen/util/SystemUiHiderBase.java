package com.example.fullscreen.util;

import android.view.View;
import android.view.WindowManager;
import android.support.v7.app.ActionBarActivity;

/**
 * A base implementation of {@link SystemUiHider}. Uses APIs available in all
 * API levels to show and hide the status bar.
 */
public class SystemUiHiderBase extends SystemUiHider
{
	/**
	 * Whether or not the system UI is currently visible. This is a cached value
	 * from calls to {@link #hide()} and {@link #show()}.
	 */
	private boolean mVisible = true;

	/**
	 * Constructor not intended to be called by clients. Use
	 * {@link SystemUiHider#getInstance} to obtain an instance.
	 */
	protected SystemUiHiderBase( ActionBarActivity activity, View anchorView, int flags )
	{
		super( activity, anchorView, flags );
	}

	@Override
	public void setup( )
	{
		//Do Something!
	}

	@Override
	public boolean isVisible( )
	{
		return mVisible;
	}

	@Override
	public void hide( )
	{
		if( ( mFlags & FLAG_FULLSCREEN ) != 0 )
		{
			mActivity.getWindow( ).setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );
		}
		mActivity.getSupportActionBar( ).hide( );
		mOnVisibilityChangeListener.onVisibilityChange( false );
		mVisible = false;
	}

	@Override
	public void show( )
	{
		// If you want only reshow only actionBar comment this if
		if( ( mFlags & FLAG_FULLSCREEN ) != 0 )
		{
			mActivity.getWindow( ).setFlags( 0,
																			WindowManager.LayoutParams.FLAG_FULLSCREEN );
		}
		mActivity.getSupportActionBar( ).show( );
		mOnVisibilityChangeListener.onVisibilityChange( true );
		mVisible = true;
	}
}
