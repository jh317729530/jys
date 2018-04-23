package com.gunn.jys.configuration;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.gunn.jys.elasticsearch.dao")
public class ElasticsearchConfiguration {

//    @Value("${elasticsearch.host}")
//    private String EsHost;
//
//    @Value("${elasticsearch.port}")
//    private int EsPort;
//
//    @Value("${elasticsearch.clustername}")
//    private String EsCulsterName;
//
//    @Bean
//    public Client client() throws UnknownHostException {
//        Settings esSettings = Settings.builder().put("cluster.name", EsCulsterName).put("client.transport.sniff",true).build();
//        return new PreBuiltTransportClient(esSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), EsPort));
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
//        return new ElasticsearchTemplate(client());
//    }

//    @Bean
//    public TransportClient client() throws UnknownHostException {
//                Settings esSettings = Settings.builder().put("cluster.name", "elasticsearch").put("client.transport.sniff",true).build();
//        return new PreBuiltTransportClient(esSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//    }
//
//    @Bean
//    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
//        return new ElasticsearchTemplate(client());
//    }
}
