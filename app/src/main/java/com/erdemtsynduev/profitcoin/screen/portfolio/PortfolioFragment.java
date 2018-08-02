package com.erdemtsynduev.profitcoin.screen.portfolio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;

import butterknife.ButterKnife;

public class PortfolioFragment extends MvpAppCompatFragment implements PortfolioView {

    @InjectPresenter
    PortfolioPresenter mPortfolioPresenter;

    public static PortfolioFragment getInstance() {
        return new PortfolioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override
    public void showPortfolioList() {

    }

    @Override
    public void showEmptyPortfolioList() {

    }
}
