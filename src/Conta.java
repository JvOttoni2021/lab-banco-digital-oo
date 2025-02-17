import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {

	private static final double TAXA_OPERACAO = 0.10;
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected List<Cliente> proponentes;

	public Conta(Cliente cliente) {
		proponentes = new ArrayList<>();
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.proponentes.add(cliente);
	}

	@Override
	public void sacar(double valor) {
		valor = incluirTaxa(valor);
		if (saldo - valor < 0){
			printMensagemSaldoInvalido("Saque");
			return;
		}
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		valor = incluirTaxa(valor);
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if (saldo - valor < 0){
			printMensagemSaldoInvalido("Transferência");
			return;
		}
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	private void imprimirProponentes() {
		for (int i = 0; i < this.proponentes.size(); i++) {
			System.out.printf("Proponente %d: %s%n", i, this.proponentes.get(i));
		}
	}

	protected void imprimirInfosComuns() {
		this.imprimirProponentes();
		System.out.printf("Agencia: %d%n", this.agencia);
		System.out.printf("Numero: %d%n", this.numero);
		System.out.printf("Saldo: %.2f%n", this.saldo);
	}

	private void printMensagemSaldoInvalido(String operacao) {
		System.out.println("Saldo indisponível. Conta: " + this.getNumero() + ", Operação: " + operacao);
	}

	protected double incluirTaxa(double valor) {
		System.out.println("Incluindo taxa de operação.");
		return valor + valor * TAXA_OPERACAO;
	}
}
