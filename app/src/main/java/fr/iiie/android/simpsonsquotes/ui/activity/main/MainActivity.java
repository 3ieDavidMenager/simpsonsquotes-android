package fr.iiie.android.simpsonsquotes.ui.activity.main;

import android.content.IntentFilter;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.iiie.android.simpsonsquotes.R;
import fr.iiie.android.simpsonsquotes.bus.SnackEvent;
import fr.iiie.android.simpsonsquotes.bus.SwitchFragmentEvent;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.business.main.MainController;
import fr.iiie.android.simpsonsquotes.ui.fragment.search.SearchFragment;

public class MainActivity extends AppCompatActivity
{
    private MainController mainController;

    @BindView(R.id.drawer_container)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.drawer_navigation_view)
    protected NavigationView navigationView;

    @BindView(R.id.activity_main_container)
    protected View containerView;

    @BindView(R.id.activity_main_toolbar)
    protected Toolbar actionBar;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final Point screenSizePoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSizePoint);
        App.setScreenSize(screenSizePoint);
        mainController = new MainController();
        setSupportActionBar(actionBar);

        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, actionBar, R.string.openDrawer, R.string.closeDrawer)
        {

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
            }

        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.drawer_menu_item_close:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    default:
                        App.getAppBus().post(new SnackEvent("You clicked : " + menuItem.getTitle()));
                        break;
                }

                return true;
            }
        });

        if (getSupportFragmentManager().findFragmentByTag("search") == null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main_container, new SearchFragment(), "search")
                    .commit();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mainController.resume();
        registerReceiver(App.getNetworkBroadcastReceiver(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        if (!App.getAppBus().isRegistered(this))
        {
            App.getAppBus().register(this);
        }
    }

    @Override
    protected void onPause()
    {
        if (App.getAppBus().isRegistered(this))
        {
            App.getAppBus().unregister(this);
        }
        unregisterReceiver(App.getNetworkBroadcastReceiver());
        mainController.pause();
        super.onPause();
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchFragmentEvent(SwitchFragmentEvent switchFragmentEvent)
    {
        if (switchFragmentEvent.isToCleanBackstack())
        {
            cleanBackstack();
        }
        if (switchFragmentEvent.getFragment() != null)
        {
            final String tag = ((Object) switchFragmentEvent.getFragment()).getClass().getName();

            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (switchFragmentEvent.getDirection() != null)
            {
                if (switchFragmentEvent.getDirection() == SwitchFragmentEvent.Direction.VERTICAL)
                {
                    transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out);
                }
                else if (switchFragmentEvent.getDirection() == SwitchFragmentEvent.Direction.HORIZONTAL)
                {
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                }
                else
                {
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                }
            }

            if (switchFragmentEvent.isFragmentReplaced())
            {
                transaction.replace(R.id.activity_main_container, switchFragmentEvent.getFragment(), tag);
            }
            else
            {
                transaction.add(R.id.activity_main_container, switchFragmentEvent.getFragment(), tag);
            }

            if (switchFragmentEvent.isAddedToBackStack())
            {
                transaction.addToBackStack(null);
            }

            transaction.commit();
        }
        else
        {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSnackEvent(SnackEvent event)
    {
        Snackbar.make(containerView, event.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    private void cleanBackstack()
    {
        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
