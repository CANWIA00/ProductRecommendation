package com.canwia.ecommerceads.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;


@UtilityClass
public class EsUtil {


    public static Query createMatchAllQuery(){
        return Query.of(q->q.matchAll(new MatchAllQuery.Builder().build()));
    }

    public static Supplier<Query> buildFieldForFieldAndValue(String field, String value) {
        return () -> Query.of(q->q.match(buildMatchQueryForFieldAndValue(field,value)));
    }

    private static MatchQuery buildMatchQueryForFieldAndValue(String field, String value) {
        return new MatchQuery.Builder()
                .field(field)
                .query(value)
                .build();
    }


    public static Query suggestedQuery(String name) {

        return Query.of(q->q.match(
                new MatchQuery.Builder()
                        .field("name")
                        .query(name)
                        .analyzer("custom_index")
                        .build()
        ));
    }
}
