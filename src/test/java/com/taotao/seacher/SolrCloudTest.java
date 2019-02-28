package com.taotao.seacher;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrCloudTest {
	
	
	@Test
	public void testSolrCloudAdd() throws Exception {
		//创建一个solrj集群
		//zkHost就是zookeeper地址列表，用逗号分隔
		String zkHost = "192.168.198.128:2181,192.168.198.128:2182,192.168.198.128:2183";
		CloudSolrServer cloudSolrServer = new CloudSolrServer(zkHost);
		//创建一个默认collection
		cloudSolrServer.setDefaultCollection("collection2");
		//创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		//添加域
		document.addField("id", "test001");
		document.addField("item_title", "测试数据");
		
		cloudSolrServer.add(document);
		
		cloudSolrServer.commit();
		
	}
}
