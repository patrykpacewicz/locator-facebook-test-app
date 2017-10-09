package pl.ppac.infrastructure.swagger

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{ResourceHandlerRegistry, ViewControllerRegistry, WebMvcConfigurerAdapter}

@Configuration
class DocumentationPathConfig extends WebMvcConfigurerAdapter {
  override def addViewControllers(registry: ViewControllerRegistry): Unit = {
    registry.addRedirectViewController(s"${DocumentationPathConfig.Documentation}/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true)
    registry.addRedirectViewController(s"${DocumentationPathConfig.Documentation}/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui")
    registry.addRedirectViewController(s"${DocumentationPathConfig.Documentation}/swagger-resources/configuration/security", "/swagger-resources/configuration/security")
    registry.addRedirectViewController(s"${DocumentationPathConfig.Documentation}/swagger-resources", "/swagger-resources")
    registry.addRedirectViewController(s"${DocumentationPathConfig.Documentation}", s"${DocumentationPathConfig.Documentation}/swagger-ui.html")
    registry.addRedirectViewController(s"${DocumentationPathConfig.Documentation}/", s"${DocumentationPathConfig.Documentation}/swagger-ui.html")
  }

  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry.addResourceHandler(s"${DocumentationPathConfig.Documentation}/**").addResourceLocations("classpath:/META-INF/resources/")
  }
}

object DocumentationPathConfig {
  final val Documentation = "/documentation"
}