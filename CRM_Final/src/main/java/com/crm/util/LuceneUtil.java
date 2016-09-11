package com.crm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.crm.bean.DataDirectory;

public class LuceneUtil {
	private static Directory directory;
	private IndexWriter writer;
	private IndexReader reader;
	private List<DataDirectory> list;
	public LuceneUtil(List<DataDirectory> list,String path){
		this.list=list;
		try {
			//设置索引路径
			directory=FSDirectory.open(new File(path+"..//"+"index"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public LuceneUtil(){
	}
	public static Directory getDirectory() {
		return directory;
	}

	public IndexWriter getWriter(OpenMode createOrAppend) {
		if (writer != null)
			return writer;

		Analyzer analyzer = new MMSegAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_4, analyzer);
		if (createOrAppend == null)
			conf.setOpenMode(OpenMode.CREATE);
		else
			conf.setOpenMode(createOrAppend);

		try {
			writer = new IndexWriter(directory, conf);
			return writer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public IndexReader getIndexReader() {
		try {
			DirectoryReader newReader = null;
			if (reader == null)
				reader = DirectoryReader.open(directory);
			else
				newReader = DirectoryReader.openIfChanged((DirectoryReader) reader);
			if (newReader != null)
				reader = newReader;
			return reader;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public IndexSearcher getIndexSearcher() {
		return new IndexSearcher(getIndexReader());
	}

	public void index() throws Exception {
		Document document = null;
		writer = getWriter(OpenMode.CREATE);

		System.out.println(list);
		for (DataDirectory vo : list) {
			document = new Document();
			try {
				Field field = new TextField("dname", vo.getDname(), Store.YES);
				document.add(field);
				Field field1 = new TextField("dtype", vo.getDtype(), Store.YES);
				document.add(field1);
				if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
					System.out.println("adding " + vo);
					writer.addDocument(document);
				} else {
					System.out.println("updating " + vo);
					writer.updateDocument(new Term("path", vo.toString()), document);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if (writer != null)
				writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<DataDirectory> search(String queryStr, int num) throws InvalidTokenOffsetsException {
		Analyzer analyzer = new MMSegAnalyzer();
		QueryParser parser = new QueryParser("dname", analyzer);
		IndexSearcher searcher = getIndexSearcher();
		List<DataDirectory> datalist = new ArrayList<DataDirectory>();
		try {
			Query query = parser.parse(queryStr);
			TopDocs docs = searcher.search(query, num);

			Set<String> fieldSet = new HashSet<String>();
			fieldSet.add("dname");
			fieldSet.add("dtype");
			for (ScoreDoc scoreDoc : docs.scoreDocs) {
				Document document = searcher.doc(scoreDoc.doc, fieldSet);
				DataDirectory dataDirectory = new DataDirectory();
				System.out.println("dname====" + document.get("dname"));
				System.out.println("dtype====" + document.get("dtype"));
				
				dataDirectory.setDname(document.get("dname"));
				dataDirectory.setDtype(document.get("dtype"));
				datalist.add(dataDirectory);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return datalist;
	}
}
