package com.erdemtsynduev.profitcoin.screen.coinlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.coindetail.CoinDetailActivity;
import com.erdemtsynduev.profitcoin.screen.coinlist.adapter.CoinListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinListFragment extends MvpAppCompatFragment implements CoinListView {

    @InjectPresenter
    CoinListPresenter mCoinListPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView mChartsListRecycler;

    private CoinListAdapter mCoinListAdapter;

    public static CoinListFragment getInstance() {
        return new CoinListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coinlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mChartsListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mChartsListRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        mChartsListRecycler.addItemDecoration(dividerItemDecoration);

        mCoinListAdapter = new CoinListAdapter(getContext());
        mCoinListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        mCoinListAdapter.setOnItemClickListener((adapter, view1, position) -> {
            if ((adapter.getItem(position)) != null) {
                mCoinListPresenter.openScreenDetail((Datum) adapter.getItem(position));
            }
        });

        mChartsListRecycler.setAdapter(mCoinListAdapter);

        mCoinListPresenter.getData();
    }

    @Override
    public void showCoinList(List<Datum> datumList) {
        mCoinListAdapter.setNewData(datumList);
    }

    @Override
    public void showEmptyCoinList() {

    }

    @Override
    public void openScreenDetail(Datum datum) {
        Intent intent = new Intent(getContext(), CoinDetailActivity.class);
        intent.putExtra("datum", datum);
        startActivity(intent);
    }
}