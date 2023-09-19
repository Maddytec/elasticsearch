package com.maddytec.elasticsearch.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.maddytec.elasticsearch.model.Product;
import com.maddytec.elasticsearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ElasticsearchClient elasticsearchClient;

    @Override
    public Product create(Product product) {
        try {
            IndexResponse indexResponse = elasticsearchClient.index(i -> i
                    .index("products")
                    .id(product.getId())
                    .document(product));
            return product;
        } catch (IOException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "The product was not created.");
        }
    }

    @Override
    public List<Product> getAllProducts() throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index("products")
                .build();
        SearchResponse<Product> productSearchResponse = elasticsearchClient.search(searchRequest, Product.class);
        List<Hit<Product>> hitList = productSearchResponse.hits().hits();

        return hitList.stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    @Override
    public Product getByProduct(String id) throws IOException {
        GetRequest getRequest = new GetRequest.Builder()
                .index("products")
                .id(id)
                .build();

        GetResponse<Product> productGetResponse = elasticsearchClient
                .get(getRequest, Product.class);

        if (productGetResponse.found()) {
            return productGetResponse.source();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
    }

    @Override
    public void updateProduct(String id, Product product) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest.Builder()
                .index("products")
                .id(id)
                .doc(product)
                .build();

        elasticsearchClient.update(updateRequest, Product.class);
    }

    @Override
    public void deleteProduct(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest.Builder()
                .index("products")
                .id(id)
                .build();

        elasticsearchClient.delete(deleteRequest);
    }
}
