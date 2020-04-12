package example

import grails.plugin.springsecurity.annotation.Secured

class Ex100Controller {

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        render view: "/ex100/example/ex100/index"
    }
}
