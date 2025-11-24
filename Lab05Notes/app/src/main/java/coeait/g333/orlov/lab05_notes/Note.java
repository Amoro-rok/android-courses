package coeait.g333.orlov.lab05_notes;

public class Note
{
    public String title;
    public String content;

    public String toString()
    {
        return this.title;
    }

    public Note()
    {
        title = "New note";
        content = "Some content";
    }

    public Note(String ttl, String cntnt)
    {
        this.title = ttl;
        this.content = cntnt;
    }
}
