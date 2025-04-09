package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.AbstractTermTuple;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class Document extends AbstractDocument {
    
    public void AbstractDocument(){}

    /**
     * 构造函数
     * @param docId：文档id
     * @param docPath：文档绝对路径
     */
    public void  AbstractDocument(int docId, String docPath){
        this.docId  = docId;
        this.docPath = docPath;
    }

    public void AbstractDocument(int docId, String docPath,List<AbstractTermTuple> tuples){
        this.docId  = docId;
        this.docPath = docPath;
        this.tuples = tuples;
    }

    /**
     * 获得文档id
     * @return ：文档id
     */
    public int getDocId()
    {
        return this.docId;
    }

    /**
     * 设置文档id
     * @param docId：文档id
     */
    public void setDocId(int docId)
    {
        this.docId = docId;
    }

    /**
     * 获得文档绝对路径
     * @return ：文档绝对路径
     */
    public String getDocPath()
    {
        return this.docPath;
    }

    /**
     * 设置文档绝对路径
     * @param docPath：文档绝对路径
     */
    public void setDocPath(String docPath)
    {
        this.docPath = docPath;
    }

    /**
     * 获得文档包含的三元组列表
     * @return ：文档包含的三元组列表
     */
    public List<AbstractTermTuple> getTuples()
    {
        return this.tuples;
    }

    /**
     * 向文档对象里添加三元组, 要求不能有内容重复的三元组
     * @param tuple ：要添加的三元组
     */
    public void addTuple(AbstractTermTuple tuple)
    {
       if(contains(tuple)) // 如果已经包含该三元组，则不添加
           return;
        this.tuples.add(tuple); // 添加三元组
    }

    /**
     * 判断是否包含指定的三元组
     * @param tuple ： 指定的三元组
     * @return ： 如果包含指定的三元组，返回true;否则返回false
     */
    public boolean contains(AbstractTermTuple tuple)
    {   
        for(AbstractTermTuple t : this.tuples) // 遍历三元组列表
        {
            if(t.equals(tuple)) // 如果包含该三元组，则返回true
                return true;
        }
        return false; // 否则返回false
    }

    /**
     * 获得指定下标位置的三元组
     * @param index：指定下标位置
     * @return：三元组
     */
    public AbstractTermTuple getTuple(int index)
    {
        if(index < 0 || index >= this.tuples.size()) // 如果下标不合法，则返回null
            return null;
        return this.tuples.get(index); // 返回指定下标位置的三元组
    }

        /**
     * 返回文档对象包含的三元组的个数
     * @return ：文档对象包含的三元组的个数
     */
    public int getTupleSize()
    {
        return this.tuples.size(); // 返回三元组列表的大小
    }

    /**
     * 获得Document的字符串表示
     * @return ： Document的字符串表示
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(); // 创建StringBuilder对象
        sb.append("Document: ").append(this.docId).append(", ").append(this.docPath).append("\n"); // 添加文档id和绝对路径
        sb.append("Tuples: ").append(this.tuples.size()).append("\n"); // 添加三元组个数
        for(AbstractTermTuple tuple : this.tuples) // 遍历三元组列表
        {
            sb.append(tuple.toString()).append("\n"); // 添加三元组字符串表示
        }
        return sb.toString(); // 返回字符串表示
    }
}
