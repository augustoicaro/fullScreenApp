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
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SystemUiHiderHoneycomb extends SystemUiHiderBase
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
	protected SystemUiHiderHoneycomb( ActionBarActivity activity,
																	 View anchorView, int flags )
	{
		super( activity, anchorView, flags );

		mToggleFlags = View.SYSTEM_UI_FLAG_FULLSCREEN
			| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
	}

	/** {@inheritDoc} */
	@Override
	public void setup( )
	{
		mAnchorView
			.setOnSystemUiVisibilityChangeListener( mSystemUiVisibilityChangeListener );
	}

	/** {@inheritDoc} */
	@Override
	public void hide( )
	{		
		uiOptions = mActivity.getWindow( ).getDecorView( ).getSystemUiVisibility( );
		uiOptions |= mToggleFlags;
		mAnchorView.setSystemUiVisibility( uiOptions );
	}

	/** {@inheritDoc} */
	@Override
	public void show( )
	{
		uiOptions = mActivity.getWindow( ).getDecorView( ).getSystemUiVisibility( );
		uiOptions &= ~mToggleFlags;
		mAnchorView.setSystemUiVisibility( uiOptions );
	}

	/** {@inheritDoc} */
	@Override
	public boolean isVisible( )
	{
		return mVisible;
	}

	private View.OnSystemUiVisibilityChangeListener mSystemUiVisibilityChangeListener = new View.OnSystemUiVisibilityChangeListener( ) {
		@Override
		public void onSystemUiVisibilityChange( int vis )
		{
			// Test if the system UI is visible.
			if( vis != 0 )
			{
				// Hide Action Bar Compat
				mActivity.getSupportActionBar( ).hide( );
				// Hide status bar
				mActivity.getWindow( ).setFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN );

				// Trigger the registered listener and cache the visibility
				// state.
				mOnVisibilityChangeListener.onVisibilityChange( false );
				mVisible = false;

			}
			else
			{
				// Show Action Bar Compat
				mActivity.getSupportActionBar( ).show( );
				// Show status bar. Comment command bellow if you want show
				// only action bar
				mActivity.getWindow( ).setFlags( 0,
																				WindowManager.LayoutParams.FLAG_FULLSCREEN );

				// Trigger the registered listener and cache the visibility
				// state.
				mOnVisibilityChangeListener.onVisibilityChange( true );
				mVisible = true;
			}
		}
	};
}
