FROM maven:3-jdk-8-alpine as build

WORKDIR /app

RUN apk add --no-cache npm apache-ant

# copy pom files
COPY Apromore-Assembly/Custom-Plugins-Assembly/pom.xml Apromore-Assembly/Custom-Plugins-Assembly/
COPY Apromore-Assembly/Manager-Assembly/pom.xml Apromore-Assembly/Manager-Assembly/
COPY Apromore-Assembly/pom.xml Apromore-Assembly/
COPY Apromore-Assembly/Portal-Assembly/pom.xml Apromore-Assembly/Portal-Assembly/
COPY Apromore-Clients/manager-client/pom.xml Apromore-Clients/manager-client/
COPY Apromore-Clients/manager-security/pom.xml Apromore-Clients/manager-security/
COPY Apromore-Clients/portal-model/pom.xml Apromore-Clients/portal-model/
COPY Apromore-Core-Components/Apromore-BPMNEditor/pom.xml Apromore-Core-Components/Apromore-BPMNEditor/
COPY Apromore-Core-Components/Apromore-Manager/pom.xml Apromore-Core-Components/Apromore-Manager/
COPY Apromore-Core-Components/Apromore-Portal/pom.xml Apromore-Core-Components/Apromore-Portal/
COPY Apromore-Custom-Plugins/About-Portal-Plugin/pom.xml Apromore-Custom-Plugins/About-Portal-Plugin/
COPY Apromore-Custom-Plugins/Account-Portal-Plugin/pom.xml Apromore-Custom-Plugins/Account-Portal-Plugin/
COPY Apromore-Custom-Plugins/Apromore-Editor/bpmn20xmlbasic/pom.xml Apromore-Custom-Plugins/Apromore-Editor/bpmn20xmlbasic/
COPY ["Apromore-Custom-Plugins/Apromore-Editor/diagram core/pom.xml", "Apromore-Custom-Plugins/Apromore-Editor/diagram core/"]
COPY Apromore-Custom-Plugins/Apromore-Editor/pdf/pom.xml Apromore-Custom-Plugins/Apromore-Editor/pdf/
COPY Apromore-Custom-Plugins/Apromore-Editor/pom.xml Apromore-Custom-Plugins/Apromore-Editor/
COPY Apromore-Custom-Plugins/CSVExporter-Logic/pom.xml Apromore-Custom-Plugins/CSVExporter-Logic/
COPY Apromore-Custom-Plugins/CSVExporter-Portal/pom.xml Apromore-Custom-Plugins/CSVExporter-Portal/
COPY Apromore-Custom-Plugins/CSVImporter-Logic/pom.xml Apromore-Custom-Plugins/CSVImporter-Logic/
COPY Apromore-Custom-Plugins/CSVImporter-Portal/pom.xml Apromore-Custom-Plugins/CSVImporter-Portal/
COPY Apromore-Custom-Plugins/ETLPlugin-Logic/pom.xml Apromore-Custom-Plugins/ETLPlugin-Logic/
COPY Apromore-Custom-Plugins/ETLPlugin-Portal/pom.xml Apromore-Custom-Plugins/ETLPlugin-Portal/
COPY Apromore-Custom-Plugins/File-Portal-Plugin/pom.xml Apromore-Custom-Plugins/File-Portal-Plugin/
COPY Apromore-Custom-Plugins/Generic-Portal-Plugin/pom.xml Apromore-Custom-Plugins/Generic-Portal-Plugin/
COPY Apromore-Custom-Plugins/Log-Animation-Logic/pom.xml Apromore-Custom-Plugins/Log-Animation-Logic/
COPY Apromore-Custom-Plugins/Log-Animation-Portal-Plugin/pom.xml Apromore-Custom-Plugins/Log-Animation-Portal-Plugin/
COPY Apromore-Custom-Plugins/Log-Filter-Portal-Plugin-Generic/pom.xml Apromore-Custom-Plugins/Log-Filter-Portal-Plugin-Generic/
COPY Apromore-Custom-Plugins/Log-Logic/pom.xml Apromore-Custom-Plugins/Log-Logic/
COPY Apromore-Custom-Plugins/Merge-Logic/pom.xml Apromore-Custom-Plugins/Merge-Logic/
COPY Apromore-Custom-Plugins/Merge-Portal-Plugin/pom.xml Apromore-Custom-Plugins/Merge-Portal-Plugin/
COPY Apromore-Custom-Plugins/Process-Discoverer-Logic/pom.xml Apromore-Custom-Plugins/Process-Discoverer-Logic/
COPY Apromore-Custom-Plugins/Process-Discoverer-Portal-Plugin/pom.xml Apromore-Custom-Plugins/Process-Discoverer-Portal-Plugin/
COPY Apromore-Custom-Plugins/Similarity-Search-Logic/pom.xml Apromore-Custom-Plugins/Similarity-Search-Logic/
COPY Apromore-Custom-Plugins/Similarity-Search-Portal-Plugin/pom.xml Apromore-Custom-Plugins/Similarity-Search-Portal-Plugin/
COPY Apromore-Custom-Plugins/SplitMiner-Logic/pom.xml Apromore-Custom-Plugins/SplitMiner-Logic/
COPY Apromore-Custom-Plugins/User-Admin-Portal-Plugin/pom.xml Apromore-Custom-Plugins/User-Admin-Portal-Plugin/
COPY Apromore-Extras/APMLogModule/pom.xml Apromore-Extras/APMLogModule/
COPY Apromore-Extras/Build-Tools/pom.xml Apromore-Extras/Build-Tools/
COPY Apromore-Extras/Chiaroscuro-Theme/pom.xml Apromore-Extras/Chiaroscuro-Theme/
COPY Apromore-Extras/OpenXES/pom.xml Apromore-Extras/OpenXES/
COPY Apromore-Extras/Test-Tools/pom.xml Apromore-Extras/Test-Tools/
COPY Apromore-OSGI-Bundles/eclipse-collections-osgi/pom.xml Apromore-OSGI-Bundles/eclipse-collections-osgi/
COPY Apromore-OSGI-Bundles/hpi-bpt-osgi/pom.xml Apromore-OSGI-Bundles/hpi-bpt-osgi/
COPY Apromore-OSGI-Bundles/jgraph-osgi/pom.xml Apromore-OSGI-Bundles/jgraph-osgi/
COPY Apromore-OSGI-Bundles/json-osgi/pom.xml Apromore-OSGI-Bundles/json-osgi/
COPY Apromore-OSGI-Bundles/log-osgi/pom.xml Apromore-OSGI-Bundles/log-osgi/
COPY Apromore-OSGI-Bundles/lpsolve-osgi/pom.xml Apromore-OSGI-Bundles/lpsolve-osgi/
COPY Apromore-OSGI-Bundles/prom-bpmn-osgi/pom.xml Apromore-OSGI-Bundles/prom-bpmn-osgi/
COPY Apromore-OSGI-Bundles/prom-models-osgi/pom.xml Apromore-OSGI-Bundles/prom-models-osgi/
COPY Apromore-OSGI-Bundles/prom-widgets-osgi/pom.xml Apromore-OSGI-Bundles/prom-widgets-osgi/
COPY Apromore-OSGI-Bundles/similaritysearch-osgi/pom.xml Apromore-OSGI-Bundles/similaritysearch-osgi/
COPY Apromore-OSGI-Bundles/zk-osgi/pom.xml Apromore-OSGI-Bundles/zk-osgi/
COPY Apromore-Plugins/plugin-core/core/api/pom.xml Apromore-Plugins/plugin-core/core/api/
COPY Apromore-Plugins/plugin-core/core/provider/pom.xml Apromore-Plugins/plugin-core/core/provider/
COPY Apromore-Plugins/plugin-core/editor/api/pom.xml Apromore-Plugins/plugin-core/editor/api/
COPY Apromore-Plugins/plugin-core/portal/api/pom.xml Apromore-Plugins/plugin-core/portal/api/
COPY Apromore-Plugins/plugin-database/database-h2/pom.xml Apromore-Plugins/plugin-database/database-h2/
COPY Apromore-Plugins/plugin-database/database-mysql/pom.xml Apromore-Plugins/plugin-database/database-mysql/
COPY Apromore-Plugins/plugin-database/database-oracle/pom.xml Apromore-Plugins/plugin-database/database-oracle/
COPY Apromore-Plugins/plugin-database/database-postgresql/pom.xml Apromore-Plugins/plugin-database/database-postgresql/
COPY Apromore-Plugins/plugin-templates/portal-custom-gui/pom.xml Apromore-Plugins/plugin-templates/portal-custom-gui/
COPY Apromore-Plugins/pom.xml Apromore-Plugins/
COPY Event-Log-Plugin-API/pom.xml Event-Log-Plugin-API/
COPY pom.xml ./
COPY Process-Plugin-API/pom.xml Process-Plugin-API/

RUN mvn de.qaware.maven:go-offline-maven-plugin:resolve-dependencies

RUN npm i -g less

COPY . .

RUN mvn -B -T 1C package -DskipTests
RUN ant deploy-virgo
RUN cp site.properties.docker ./Apromore-Assembly/virgo-tomcat-server-3.6.4.RELEASE/repository/usr/site.properties

FROM openjdk:8-alpine

RUN apk add --no-cache bash

WORKDIR /apromore

# Needed for ETL plugin
RUN mkdir -p /tmp/etl_data && \
    chmod -R 777 /tmp/etl_data

COPY --from=build /app/Apromore-Assembly/virgo-tomcat-server-3.6.4.RELEASE/ /apromore/

ENV JAVA_OPTS="-server"

CMD ["/apromore/bin/startup.sh", "-clean"]
