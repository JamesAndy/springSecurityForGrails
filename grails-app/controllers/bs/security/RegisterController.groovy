package bs.security

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional
@Secured('permitAll')
class RegisterController {

    static allowedMethods = [register: "POST"]

    def index() { }

    def register() {
        if(!params.password.equals(params.repassword)) {
            flash.message = g.message(code: "bs.security.register.false.label")
            redirect action: "index"
            return
        } else {
            try {
                def userI

                if(User.findByUsername(params.username)){
                    flash.message = g.message(code: "bs.security.register.false2.label")
                    redirect action: "index"
                }
                else{
                    userI = new User()
                    userI.manCreated = 'registerPage'
                    userI.dateCreated = new Date()
                    userI.password = params.password
                    userI.username = params.username
                    userI.fullname = params.fullname
                    userI.validate()
                    if (userI.hasErrors() || !userI.save(flush: true)) { //失敗
                        def errorColumn = []
                        userI.errors.allErrors.eachWithIndex  {item, index ->
                            errorColumn[index] = [item?.arguments,item?.defaultMessage]
                        }
                        userI.discard()
                        flash.message = userI.errors.allErrors
                        redirect action: "index"
                    }
                }

                def role = Role.get(params.role.id)
                if(userI && role) {
                    UserRole.create userI, role

                    UserRole.withSession {
                        it.flush()
                        it.clear()
                    }

                    UserRole userRoleI = new UserRole()
                    userRoleI.manCreated = 'registerPage'
                    userRoleI.dateCreated = new Date()
                    userRoleI.user = userI
                    userRoleI.role = role
                    userRoleI.validate()
                    if (userRoleI.hasErrors() || !userRoleI.save(flush: true)) { //失敗
                        def errorColumn = []
                        userRoleI.errors.allErrors.eachWithIndex  {item, index ->
                            errorColumn[index] = [item?.arguments,item?.defaultMessage]
                        }
                        userRoleI.discard()
                        flash.message = g.message(code: "bs.security.register.false2.label")
                        redirect action: "index"
                        return
                    }

                    flash.message = g.message(code: "bs.security.register.success.label")
                    redirect controller: "login", action: "auth"
                } else {
                    flash.message = g.message(code: "bs.security.register.false1.label")
                    render view: "index"
                    return
                }
            } catch (ValidationException e) {
                flash.message = g.message(code: "bs.security.register.false1.label")+e.message.toString()
                redirect action: "index"
                return
            }
        }
    }
}
