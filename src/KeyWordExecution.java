package script;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import utility.ReadExcel;


public class KeyWordExecution {
	
	
	public void runReflectionMethod(String strClassName, String strMethodName,
			Object... inputArgs) {

		Class<?> params[] = new Class[inputArgs.length];

		for (int i = 0; i < inputArgs.length; i++) {
			if (inputArgs[i] instanceof String) {
				params[i] = String.class;
			}
		}
		try {
			Class<?> cls = Class.forName(strClassName);
			Object _instance = cls.newInstance();
			Method myMethod = cls.getDeclaredMethod(strMethodName, params);
			myMethod.invoke(_instance, inputArgs);

		} catch (ClassNotFoundException e) {
			System.err.format(strClassName + ":- Class not found%n");
		} catch (IllegalArgumentException e) {
			System.err
					.format("Method invoked with wrong number of arguments%n");
		} catch (NoSuchMethodException e) {
			System.err.format("In Class " + strClassName + "::" + strMethodName
					+ ":- method does not exists%n");
		} catch (InvocationTargetException e) {
			System.err.format("Exception thrown by an invoked method%n");
		} catch (IllegalAccessException e) {
			System.err
					.format("Can not access a member of class with modifiers private%n");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.err
					.format("Object cannot be instantiated for the specified class using the newInstance method%n");
		}
	}
	@Test
	public void LoginTest()
	{
		// TODO Auto-generated method stub
				KeyWordExecution exeKey = new KeyWordExecution();
				ReadExcel excelSheet = new ReadExcel();
				excelSheet.openSheet("D:/eclips/workspace/DemoProjects/KeywordFramework/TestData/testCaseSheet.xls","Home");
				for (int row = 1; row < excelSheet.getRowCount(); row++) {
					List<Object> myParamList = new ArrayList<Object>();
					String methodName = excelSheet.getValueFromCell(1, row);
					for (int col = 2; col < excelSheet.getColumnCount(); col++) {
						if (!excelSheet.getValueFromCell(col, row).isEmpty()
								& !excelSheet.getValueFromCell(col, row).equals("null")) {
							myParamList.add(excelSheet.getValueFromCell(col, row));
						}
					}

					Object[] paramListObject = new String[myParamList.size()];
					paramListObject = myParamList.toArray(paramListObject);

					exeKey.runReflectionMethod("methods.KeyWordExample",
							methodName, paramListObject);
				}
	}
//	@Test
//	public void Signup()
//	{
//		// TODO Auto-generated method stub
//				KeyWordExecution exeKey = new KeyWordExecution();
//				ReadExcel excelSheet = new ReadExcel();
//				excelSheet.openSheet("D:/eclips/workspace/DemoProjects/KeywordFramework/TestData/testCaseSheet.xls","Signup");
//				for (int row = 1; row < excelSheet.getRowCount(); row++) {
//					List<Object> myParamList = new ArrayList<Object>();
//					String methodName = excelSheet.getValueFromCell(0, row);
//					for (int col = 1; col < excelSheet.getColumnCount(); col++) {
//						if (!excelSheet.getValueFromCell(col, row).isEmpty()
//								& !excelSheet.getValueFromCell(col, row).equals("null")) {
//							myParamList.add(excelSheet.getValueFromCell(col, row));
//						}
//					}
//
//					Object[] paramListObject = new String[myParamList.size()];
//					paramListObject = myParamList.toArray(paramListObject);
//
//					exeKey.runReflectionMethod("methods.KeyWordExample",
//							methodName, paramListObject);
//				}
//	}
//	public static void main(String[] args) throws Exception{
//		// TODO Auto-generated method stub
//		KeyWordExecution exeKey = new KeyWordExecution();
//		ReadExcel excelSheet = new ReadExcel();
//		excelSheet.openSheet("D:/eclips/workspace/DemoProjects/KeywordFramework/TestData/testCaseSheet.xls","Login");
//		for (int row = 1; row < excelSheet.getRowCount(); row++) {
//			List<Object> myParamList = new ArrayList<Object>();
//			String methodName = excelSheet.getValueFromCell(0, row);
//			for (int col = 1; col < excelSheet.getColumnCount(); col++) {
//				if (!excelSheet.getValueFromCell(col, row).isEmpty()
//						& !excelSheet.getValueFromCell(col, row).equals("null")) {
//					myParamList.add(excelSheet.getValueFromCell(col, row));
//				}
//			}
//
//			Object[] paramListObject = new String[myParamList.size()];
//			paramListObject = myParamList.toArray(paramListObject);
//
//			exeKey.runReflectionMethod("methods.KeyWordExample",
//					methodName, paramListObject);
//		}
//	}
}


