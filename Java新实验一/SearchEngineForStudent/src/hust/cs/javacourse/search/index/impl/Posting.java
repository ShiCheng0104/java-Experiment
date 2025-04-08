package hust.cs.javacourse.search.index.impl;
import hust.cs.javacourse.search.index.AbstractPosting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class Posting extends AbstractPosting {
    
    @Override
public void writeObject(ObjectOutputStream out) {
    try {
        out.writeInt(docId);
        out.writeInt(freq);
        out.writeInt(positions.size());
        for (int pos : positions) {
            out.writeInt(pos);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Override
public void readObject(ObjectInputStream in) {
    try {
        docId = in.readInt();
        freq = in.readInt();
        int size = in.readInt();
        positions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            positions.add(in.readInt());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public Posting() {
        this.positions = new ArrayList<>();
    }

    public Posting(int docId, int freq, List<Integer> positions) {
        this.docId = docId;
        this.freq = freq;
        this.positions = new ArrayList<>(positions);
    }

    /**
     * 判断二个Posting内容是否相同
     * @param obj ：要比较的另外一个Posting
     * @return 如果内容相等返回true，否则返回false
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Posting other = (Posting) obj;
        return docId == other.docId &&
                freq == other.freq &&
                Objects.equals(positions, other.positions);
    }

    /**
     * 返回Posting的字符串表示
     * @return 字符串
     */
    @Override
    public  String toString()
    {
        return "docId:" + docId + ", freq:" + freq + ", positions:" + positions;
    }

    /**
     * 返回包含单词的文档id
     * @return ：文档id
     */
    public int getDocId()
    {
        return docId;
    }

    /**
     * 设置包含单词的文档id
     * @param docId：包含单词的文档id
     */
    public  void setDocId(int docId)
    {
        this.docId = docId;
    }

    /**
     * 返回单词在文档里出现的次数
     * @return ：出现次数
     */
    public int getFreq()
    {
        return freq;
    }

    /**
     * 设置单词在文档里出现的次数
     * @param freq:单词在文档里出现的次数
     */
    public void setFreq(int freq)
    {
        this.freq = freq;
    }

    /**
     * 返回单词在文档里出现的位置列表
     * @return ：位置列表
     */
    public  List<Integer> getPositions()
    {
        return Collections.unmodifiableList(positions);
    }

    /**
     * 设置单词在文档里出现的位置列表
     * @param positions：单词在文档里出现的位置列表
     */
    public void setPositions(List<Integer> positions)
    {
        this.positions = new ArrayList<>(positions);
    }

    /**
     * 比较二个Posting对象的大小（根据docId）
     * @param o： 另一个Posting对象
     * @return ：二个Posting对象的docId的差值
     */
    @Override
    public int compareTo(AbstractPosting o)
    {
        return this.docId - ((Posting) o).docId;
    }

    /**
     * 对内部positions从小到大排序
     */
    public void sort()
    {
        Collections.sort(positions);
    }
}
