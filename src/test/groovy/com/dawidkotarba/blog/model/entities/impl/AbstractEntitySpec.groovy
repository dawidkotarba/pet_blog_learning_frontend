package com.dawidkotarba.blog.model.entities.impl

import spock.lang.Specification

class AbstractEntitySpec extends Specification {

    def "Should be able to retrieve entities from hash collection by using UUID"() {
        given:
        def user1 = new UserEntity()
        def collection = [user1, new UserEntity()] as HashSet

        when:
        def isRetrievable = false;
        collection.each {
            if (it == user1) {
                isRetrievable = true;
            }
        }

        then:
        isRetrievable == true;
    }
}
