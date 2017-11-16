HOMEPAGE = "https://github.com/firehol/netdata/"
SUMMARY = "Real-time performance monitoring"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=95b49e9ea979a337578f13c2a3ab9535 \
                    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                   "

SRC_URI = "git://github.com/firehol/netdata.git;protocol=https \
           file://0001-makefile-Do-not-build-contrib-dir.patch \
"
SRCREV = "v1.8.0"
PV = "1.8.0+git${SRCPV}"

# default netdata.conf for netdata configuration
SRC_URI += "file://netdata.conf"

S = "${WORKDIR}/git"

DEPENDS += "zlib util-linux"

inherit pkgconfig autotools useradd systemd

#User specific
USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system netdata"

do_install_append() {
    # Install default netdata.conf
    install -d ${D}${sysconfdir}/netdata
    install -m 0644 ${WORKDIR}/netdata.conf ${D}${sysconfdir}/netdata/
    sed -i -e 's,@@sysconfdir,${sysconfdir},g' ${D}${sysconfdir}/netdata/netdata.conf
    sed -i -e 's,@@libdir,${libexecdir},g' ${D}${sysconfdir}/netdata/netdata.conf
    sed -i -e 's,@@datadir,${datadir},g' ${D}${sysconfdir}/netdata/netdata.conf
    install -d ${D}/var/lib/netdata
    install -d ${D}/var/log/netdata
}

FILES_${PN}-dbg += "${libexecdir}/netdata/plugins.d/.debug"
RDEPENDS_${PN} = "bash zlib"
