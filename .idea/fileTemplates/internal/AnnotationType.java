#if (${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

#if (${IMPORT_BLOCK} != "")${IMPORT_BLOCK}
#end

/**
 * Created by ${USER} on ${DATE}.
 */
#parse("File Header.java")
#if (${VISIBILITY} == "PUBLIC")public #end @interface ${NAME} #if (${INTERFACES} != "")extends ${INTERFACES} #end {
}