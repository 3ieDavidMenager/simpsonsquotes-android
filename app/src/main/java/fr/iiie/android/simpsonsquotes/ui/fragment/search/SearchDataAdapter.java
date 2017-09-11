package fr.iiie.android.simpsonsquotes.ui.fragment.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import fr.iiie.android.simpsonsquotes.R;
import fr.iiie.android.simpsonsquotes.bus.SwitchFragmentEvent;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.model.QuoteSearchModel;
import fr.iiie.android.simpsonsquotes.ui.fragment.details.DetailsFragment;

class SearchDataAdapter extends RecyclerView.Adapter<SearchDataAdapter.ViewHolder>
{
    private List<QuoteSearchModel> list;

    @Override
    public SearchDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_search_item, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    void setList(List<QuoteSearchModel> list)
    {
        this.list = list;
    }

    SearchDataAdapter()
    {
        //Default Constructor
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        QuoteSearchModel item = list.get(position);
        holder.display(item);
    }

    @Override
    public int getItemCount()
    {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView idText;
        TextView episodeText;
        TextView timestampText;
        ImageView imageView;
        Context context;
        QuoteSearchModel currentItem;


        ViewHolder(View itemView, Context context)
        {
            super(itemView);
            this.context = context;
            idText = (TextView) itemView.findViewById(R.id.fragment_search_item_idText);
            episodeText = (TextView) itemView.findViewById(R.id.fragment_search_item_episodeText);
            timestampText = (TextView) itemView.findViewById(R.id.fragment_search_item_timestampText);
            imageView = (ImageView) itemView.findViewById(R.id.fragment_search_item_image);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", String.format(Locale.getDefault(), "%d", currentItem.getId()));
                    bundle.putString("episode", currentItem.getEpisode());
                    bundle.putString("timestamp", String.format(Locale.getDefault(), "%d", currentItem.getTimestamp()));
                    DetailsFragment detailsFragment = new DetailsFragment();
                    detailsFragment.setArguments(bundle);
                    App.getAppBus().post(new SwitchFragmentEvent(detailsFragment, SwitchFragmentEvent.Direction.ALPHA, true, true, false));
                }
            });
        }

        void display(QuoteSearchModel item)
        {
            String id = String.format(Locale.getDefault(), "%d", item.getId());
            idText.setText(id);
            String timestamp = String.format(Locale.getDefault(), "%d", item.getTimestamp());
            episodeText.setText(item.getEpisode());
            timestampText.setText(timestamp);
            String img_url = context.getString(R.string.img_url, item.getEpisode(), timestamp, "small.jpg");
            currentItem = item;

            Glide.with(context)
                    .load(img_url)
                    .into(imageView);
        }
    }
}
