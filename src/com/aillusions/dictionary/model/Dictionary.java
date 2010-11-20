package com.aillusions.dictionary.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("dictionary")
public class Dictionary
{

    @XStreamAlias("working-list")
    private List<Pair> pairs;

    @XStreamAlias("removed-list")
    private List<Pair> trash;

    @XStreamAlias("display-name")
    private String displayName;


    public List<Pair> getPairs()
    {
        if(this.pairs == null)
        {
            this.pairs = new ArrayList<Pair>();
        }
        return this.pairs;
    }


    public void setPairs(final List<Pair> pairs)
    {
        this.pairs = pairs;
    }


    public List<Pair> getTrash()
    {

        if(this.trash == null)
        {
            this.trash = new ArrayList<Pair>();
        }
        return this.trash;
    }


    public void setTrash(final List<Pair> trash)
    {
        this.trash = trash;
    }


    public String getDisplayName()
    {
        return this.displayName;
    }


    public void setDisplayName(final String displayName)
    {
        this.displayName = displayName;
    }

}
