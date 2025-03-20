rootProject.name = "progra-avanzada2"
include("cdi_ejemplo01")
include("cdi_ejemplo02")
include("cdi_transaccion_bancaria")
include("cdiArmando")
include("spring_transaccion_bancaria")
include("cdi_transaccion_bancaria2")
include("cdi_transaccion_bancaria2")
include("cdi_transaccion_bancaria:cdi_transaccion_bancara_tx")
findProject(":cdi_transaccion_bancaria:cdi_transaccion_bancara_tx")?.name = "cdi_transaccion_bancara_tx"
include("cdi_transaccion_bancaria_tx")
