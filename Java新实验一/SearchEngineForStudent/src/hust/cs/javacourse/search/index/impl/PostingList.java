package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class PostingList extends AbstractPostingList {

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeInt(list.size());
            for (AbstractPosting posting : list) {
                posting.writeObject(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            int size = in.readInt();
            list.clear(); // 先清空原有列表
            for (int i = 0; i < size; i++) {
                Posting posting = new Posting();
                posting.readObject(in);
                list.add(posting);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    
     /**
     * 添加Posting,要求不能有内容重复的posting
     * @param posting：Posting对象
     */
    public void add(AbstractPosting posting)
    {
        if (!this.list.contains(posting)) {
            this.list.add(posting); // 如果没有重复，添加到列表中
        }
    }

    /**
     * 获得PosingList的字符串表示
     * @return ： PosingList的字符串表示
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (AbstractPosting posting : this.list) {
            sb.append(posting.toString());
            sb.append("\n");    
        }return sb.toString();
    }

    /**
     * 添加Posting列表,,要求不能有内容重复的posting
     * @param postings：Posting列表
     */
    public void add(List<AbstractPosting> postings)
    {
        for (AbstractPosting posting : postings) {
            if (!this.list.contains(posting)) {
                this.list.add(posting); // 如果没有重复，添加到列表中
            }
        }                     
    }

    /**
     * 返回指定下标位置的Posting
     * @param index ：下标
     * @return： 指定下标位置的Posting
     */
    public AbstractPosting get(int index)
    {
        for(AbstractPosting posting : this.list) {
            if (this.list.indexOf(posting) == index) {
                return posting; // 返回指定下标位置的Posting
            }
        }return null; // 如果没有找到，返回null
    }
    

    /**
     * 返回指定Posting对象的下标
     * @param posting：指定的Posting对象
     * @return ：如果找到返回对应下标；否则返回-1
     */
    public int indexOf(AbstractPosting posting)
    {
        for (AbstractPosting p : this.list) {
            if (p.equals(posting)) {
                return this.list.indexOf(p); // 返回指定Posting对象的下标
            }
        }return -1; // 如果没有找到，返回-1
    }

    /**
     * 返回指定文档id的Posting对象的下标
     * @param docId ：文档id
     * @return ：如果找到返回对应下标；否则返回-1
     */
    public int indexOf(int docId)
    {
        for(AbstractPosting posting : this.list) {
            if (posting.getDocId() == docId) {
                return this.list.indexOf(posting); // 返回指定文档id的Posting对象的下标
            }
        }return -1; // 如果没有找到，返回-1
    }

    /**
     * 是否包含指定Posting对象
     * @param posting： 指定的Posting对象
     * @return : 如果包含返回true，否则返回false
     */
    public boolean contains(AbstractPosting posting)
    {
        for (AbstractPosting p : this.list) {
            if (p.equals(posting)) {
                return true; // 如果包含返回true
            }
        }return false; // 否则返回false
    }

    /**
     * 删除指定下标的Posting对象
     * @param index：指定的下标
     */
    public void remove(int index)
    {
        for (AbstractPosting posting : this.list) {
            if (this.list.indexOf(posting) == index) {
                this.list.remove(posting); // 删除指定下标的Posting对象
                break;
            }
        }
    }

    /**
     * 删除指定的Posting对象
     * @param posting ：定的Posting对象
     */
    public void remove(AbstractPosting posting)
    {
        for (AbstractPosting p : this.list) {
            if (p.equals(posting)) {
                this.list.remove(p); // 删除指定的Posting对象
                break;
            }
        }        
    }

    /**
     * 返回PostingList的大小，即包含的Posting的个数
     * @return ：PostingList的大小
     */
    public int size()
    {
        return this.list.size(); // 返回PostingList的大小
    }

    /**
     * 清除PostingList
     */
    public void clear()
    {
        this.list.clear(); // 清除PostingList
    }

    /**
     * PostingList是否为空
     * @return 为空返回true;否则返回false
     */
    public boolean isEmpty()
    {
        return this.list.isEmpty(); // PostingList是否为空
    }

    /**
     * 根据文档id的大小对PostingList进行从小到大的排序
     */
    @Override
    public void sort() {
        this.list.sort((p1, p2) -> Integer.compare(p1.getDocId(), p2.getDocId()));
    }
}
