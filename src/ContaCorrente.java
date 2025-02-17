
public class ContaCorrente extends Conta {
	private static final int OPERACOES_GRATUITAS = 4;
	private int operacoesRealizadas = 0;

	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}

	@Override
	protected double incluirTaxa(double valor) {
		this.operacoesRealizadas++;
		if (this.operacoesRealizadas > OPERACOES_GRATUITAS) {
			System.out.println("Operações gratuítas excedidas.");
			return super.incluirTaxa(valor);
		}
		return valor;
	}
}
