package fr.iiie.android.simpsonsquotes.bus;

import android.support.v4.app.Fragment;

public class SwitchFragmentEvent
{

    private final Fragment fragment;
    private final Direction direction;
    private final Boolean bReplaceFragment;
    private final Boolean bAddToBackStack;
    private final Boolean bCleanBackstack;

    public SwitchFragmentEvent(final Fragment fragment, final Direction direction, final Boolean replaceFragment, final Boolean addToBackStack, final Boolean cleanBackstack)
    {
        this.fragment = fragment;
        this.direction = direction;
        this.bReplaceFragment = replaceFragment;
        this.bAddToBackStack = addToBackStack;
        this.bCleanBackstack = cleanBackstack;
    }

    public Fragment getFragment()
    {
        return fragment;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public Boolean isFragmentReplaced()
    {
        return bReplaceFragment;
    }

    public Boolean isAddedToBackStack()
    {
        return bAddToBackStack;
    }

    public Boolean isToCleanBackstack()
    {
        return bCleanBackstack;
    }

    public enum Direction
    {
        VERTICAL, HORIZONTAL, ALPHA
    }
}
