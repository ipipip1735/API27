#if (${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

#if (${IMPORT_BLOCK} != "")${IMPORT_BLOCK}
#end

/**
 * Created by ${USER} on ${DATE}.
 */
#parse("File Header.java")
#if (${VISIBILITY} == "PUBLIC")public #end enum ${NAME} #if (${INTERFACES} != "")implements ${INTERFACES} #end {
}