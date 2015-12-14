package factory;

/**
 * 工場
 * @author keisuke
 *
 */
public class Factory {

	protected String factoryName;

	/**
	 * 指定された名前の工場を構築します。
	 * @param factoryName 工場名
	 */
	public Factory(String factoryName) {
		this.factoryName = factoryName;
	}

	@Override
	public String toString() {
		return factoryName;
	}
}
