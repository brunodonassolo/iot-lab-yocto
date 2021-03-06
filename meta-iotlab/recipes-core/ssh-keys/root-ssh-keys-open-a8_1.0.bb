DESCRIPTION = "Dynamic generation of root user ssh keys"
LICENSE = "CECILL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CECILL-2.0;md5=574109ac4bdff61f9c3e0de892ecbd19"

S = "${WORKDIR}"
SRC_URI = "file://root_ssh_keys"

# Use systemd-tmpfiles to create volatiles files and dirs
# root@node-a8-<id>:~# ls /run/ssh/ssh_host_* 
# ssh_host_rsa_key          ssh_host_rsa_key.pub


do_install() {
  install -d                                   ${D}/${sysconfdir}/default/volatiles
  install -m 0644 ${WORKDIR}/root_ssh_keys  ${D}/${sysconfdir}/default/volatiles/20_root_ssh_keys
}
