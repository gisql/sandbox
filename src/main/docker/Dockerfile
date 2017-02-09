FROM quay.io/clearscore/cs-java

ADD https://releases.hashicorp.com/consul-template/0.18.1/consul-template_0.18.1_linux_386.zip /tmp/
RUN unzip /tmp/consul-template_0.18.1_linux_386.zip -d /usr/bin/ && rm /tmp/consul-template_0.18.1_linux_386.zip

COPY sandbox.jar /
COPY start.sh /
COPY logging-aws.xml /
COPY logging-aws.xml.ctmpl /

CMD ["/bin/bash", "start.sh"]
