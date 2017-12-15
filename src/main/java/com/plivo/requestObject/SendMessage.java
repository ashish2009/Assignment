package com.plivo.requestObject;

public class SendMessage {

	private String text;

    private Long dst;

    private Long src;

    private String url;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public Long getDst ()
    {
        return dst;
    }

    public void setDst (Long dst)
    {
        this.dst = dst;
    }

    public Long getSrc ()
    {
        return src;
    }

    public void setSrc (Long src)
    {
        this.src = src;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [text = "+text+", dst = "+dst+", src = "+src+", url = "+url+"]";
    }
}
