package view;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import control.InputListenerEditarNotaEntrada;
import model.Funcionario;
import model.FuncionarioDAO;
import model.NotaEntrada;
import model.NotaEntradaDAO;
import model.ProdutoDAO;

public class EditarNotaEntradaView extends JDialog {

	private static final long serialVersionUID = 7909862707617137771L;
	InputListenerEditarNotaEntrada listener;
	private JTable tableProduto;
	private JPanel contentPanel;
	private JLabel lblFuncionario;
	private JButton btnCancelar;
	private JButton btnGravar;
	private JComboBox<Object> comboBoxFuncionario;
	private JLabel labelCodigoProduto;
	private JLabel lblQuantidade;
	private JLabel lblPreoTotalr;
	private JTextField textTotalNota;
	private JSpinner spinnerQtde;
	private JLabel lblDataEmissao;
	private JLabel lblDataCadastro;
	private JFormattedTextField txtDataEmissao;
	private JTextField textNumeroNFE;
	private JLabel lblNumNFE;
	private JTable tableNotaEntrada;
	private JScrollPane scrollPane;
	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	ProdutoDAO produtoDAO = new ProdutoDAO();
	NotaEntradaDAO notaDAO = new NotaEntradaDAO();
	NotaEntrada nota = new NotaEntrada();
	private JTextField textPrecoCustoUnit;
	private JSeparator separator;
	private JLabel lblPrecoCustoUnit;
	private JLabel lblCNPJ;
	private JTextField textFieldCNPJ;
	private JLabel lblNomeFornec;
	private JTextField textFieldNomeFornec;
	private JLabel lblValorTotalProd;
	private JTextField textFieldVTotalProd;
	private JTextField textFieldOutrosCustos;
	private JLabel lblOutrosCustos;
	private JLabel lblChave;
	private JTextField textFieldChaveNFE;
	private JLabel lblAddProduto;
	private JComboBox<Object> comboBoxProduto;
	private JLabel lblidCadastro;
	private JTextField textFieldDataCadastro;
	private JTextField textFieldCodigoNFe;

	public static void main(String[] args) {
		try {
			EditarNotaEntradaView dialog = new EditarNotaEntradaView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EditarNotaEntradaView() {
		listener = new InputListenerEditarNotaEntrada(this);
		initialize();
		initializeListeners();

	}

	public EditarNotaEntradaView(NotaEntrada nota) {
		this.nota= nota;
		listener = new InputListenerEditarNotaEntrada(this);
		initialize();
		initializeListeners();
		preencherCampos(nota);
	}
	
	public NotaEntrada getNota() {
		return nota;
	}
	
	public void preencherCampos(NotaEntrada nota) {
		Funcionario f = funcionarioDAO.RetornaFuncionario(nota.getIdFuncionario());
		getComboBoxFuncionario().setSelectedItem(f.getIdFuncionario()+"-"+f.getNome());
		
		String[] colunas = { "Código", "Nome", "Preço Unit", "Quantidade", "Preço Total" };

		String[][] dados = notaDAO.retornaProdutosNota(nota.getIdCompra());
		
		for(int i = 0; i<dados.length;i++) {
			getComboBoxProduto().removeItem(dados[i][0]+"-"+dados[i][1]);
		}
		
		DefaultTableModel model = new DefaultTableModel(dados,colunas) {
			 /**
			 * 
			 */
			private static final long serialVersionUID = -7018342759131611914L;
			boolean[] canEdit = new boolean []{  
			            false, false, false, false, false
			        };  
			        @Override  
			        public boolean isCellEditable(int rowIndex, int columnIndex) {  
			            return canEdit [columnIndex];  
			        }
		};
		getTableNotaEntrada().setModel(model);
		this.repaint();
		this.revalidate();
		
	}

	public void initializeListeners() {
		getBtnGravar().addMouseListener(listener);
		getBtnCancelar().addMouseListener(listener);
		getBtnAddProduto().addMouseListener(listener);
	}


	public void initialize() {
		this.setModal(true);
		setBounds(100, 100, 666, 618);
		setContentPane(getContentPanel());
		setTitle("Edição da Nota de Entrada");

	}

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			contentPanel.setLayout(null);
			contentPanel.add(getBtnCancelar());
			contentPanel.add(getBtnGravar());
			contentPanel.add(getLblFuncionario());
			contentPanel.add(getComboBoxFuncionario());
			contentPanel.add(getLabelCodigoProduto());
			contentPanel.add(getLblQuantidade());
			contentPanel.add(getLblPreoTotalr());
			contentPanel.add(getTextTotalNota());
			contentPanel.add(getSpinnerQtde());
			contentPanel.add(getLblNumNFE());
			contentPanel.add(getTextNumeroNFE());
			contentPanel.add(getTextDataEmissao());
			contentPanel.add(getLblDataEmissao());
			contentPanel.add(getLblPrecoCustoUnit());
			contentPanel.add(getSeparator());
			contentPanel.add(getTextPrecoCustoUnit());
			contentPanel.add(getScrollBar());
			contentPanel.add(getLblCNPJ());
			contentPanel.add(getTextFieldCNPJ());
			contentPanel.add(getLblNomeFornec());
			contentPanel.add(getTextFieldNomeFornec());
			contentPanel.add(getLblValorTotalProd());
			contentPanel.add(getTextFieldVTotalProd());
			contentPanel.add(getTextFieldOutrosCustos());
			contentPanel.add(getLblOutrosCustos());
			contentPanel.add(getLblChave());
			contentPanel.add(getTextFieldChaveNFE());
			contentPanel.add(getBtnAddProduto());
			contentPanel.add(getComboBoxProduto());
			contentPanel.add(getLblidCadastro());
			contentPanel.add(getTextFieldDataCadastro());	
			contentPanel.add(getLblDataCadastro());
			contentPanel.add(getTextFieldCodigoNFe());
			
			JLabel imagem = new JLabel("");
			imagem.setIcon(new ImageIcon("Interno/logo3.png"));
			imagem.setBounds(275, 0, 152, 95);
			contentPanel.add(imagem);
					
		}
		return contentPanel;
	}


	public JLabel getLblDataCadastro() {
		if (lblDataCadastro == null) {
			lblDataCadastro = new JLabel("Data de Cadastro da NFe");
			lblDataCadastro.setBounds(474, 29, 166, 14);
		}
		return lblDataCadastro;
	}
	
	public JTextField getTextPrecoCustoUnit() {
		if (textPrecoCustoUnit == null) {
			textPrecoCustoUnit = new JTextField();
			textPrecoCustoUnit.setHorizontalAlignment(SwingConstants.CENTER);
			textPrecoCustoUnit.setBounds(437, 293, 130, 20);
			textPrecoCustoUnit.setColumns(10);
		}
		return textPrecoCustoUnit;
	}

	public JLabel getLblPrecoCustoUnit() {
		if (lblPrecoCustoUnit == null) {
			lblPrecoCustoUnit = new JLabel("Preço de Custo Unitário (R$)");
			lblPrecoCustoUnit.setBounds(439, 268, 176, 14);
		}
		return lblPrecoCustoUnit;
	}

	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(30, 255, 593, 2);
		}
		return separator;
	}

	public JScrollPane getScrollBar() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTableNotaEntrada());
			scrollPane.setBounds(30, 332, 593, 189);
		}
		return scrollPane;
	}

	public JTable getTableNotaEntrada() {
		if (tableNotaEntrada == null) {
			String[][] notas = null; // = notaEntDAO.listaNotaEntradaArray("");
			String[] colunas = { "Código", "Nome", "Preço Unit", "Quantidade", "Preço Total" };

			DefaultTableModel model = new DefaultTableModel(notas, colunas) {
				/**
				* 
				*/
				private static final long serialVersionUID = -7018342759131611914L;
				boolean[] canEdit = new boolean[] { false, false, false, false, false };

				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};
			tableNotaEntrada = new JTable(model);
			tableNotaEntrada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tableNotaEntrada;
	}

	private JLabel getLblFuncionario() {
		if (lblFuncionario == null) {
			lblFuncionario = new JLabel("Funcion\u00E1rio");
			lblFuncionario.setBounds(183, 151, 75, 14);
		}
		return lblFuncionario;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(534, 532, 89, 23);
		}
		return btnCancelar;
	}

	public JButton getBtnGravar() {
		if (btnGravar == null) {
			btnGravar = new JButton("Gravar");
			btnGravar.setBounds(426, 532, 89, 23);
		}
		return btnGravar;
	}

	public JComboBox<Object> getComboBoxFuncionario() {
		if (comboBoxFuncionario == null) {
			String funcionarios[] = funcionarioDAO.buscarNomeeId();
			comboBoxFuncionario = new JComboBox<Object>(funcionarios);
			comboBoxFuncionario.setBackground(Color.LIGHT_GRAY);
			comboBoxFuncionario.setEnabled(false);
			comboBoxFuncionario.setBounds(183, 176, 281, 20);
		}
		return comboBoxFuncionario;
	}

	public JLabel getLabelCodigoProduto() {
		if (labelCodigoProduto == null) {
			labelCodigoProduto = new JLabel("C\u00F3digo - Nome do Produto");
			labelCodigoProduto.setBounds(30, 268, 312, 14);
		}
		return labelCodigoProduto;
	}

	public JLabel getLblQuantidade() {
		if (lblQuantidade == null) {
			lblQuantidade = new JLabel("Quantidade");
			lblQuantidade.setBounds(352, 268, 89, 14);
		}
		return lblQuantidade;
	}

	public JLabel getLblPreoTotalr() {
		if (lblPreoTotalr == null) {
			lblPreoTotalr = new JLabel("Pre\u00E7o Total (R$)");
			lblPreoTotalr.setBounds(474, 207, 141, 14);
		}
		return lblPreoTotalr;
	}

	public JTextField getTextTotalNota() {
		if (textTotalNota == null) {
			textTotalNota = new JTextField();
			textTotalNota.setBackground(Color.LIGHT_GRAY);
			textTotalNota.setDisabledTextColor(Color.BLACK);
			textTotalNota.setEnabled(false);
			textTotalNota.setHorizontalAlignment(SwingConstants.CENTER);
			textTotalNota.setEditable(false);
			textTotalNota.setBounds(474, 224, 152, 20);
			textTotalNota.setText("0");
			textTotalNota.setColumns(10);
			textTotalNota.setEditable(false);
			textTotalNota.setText(""+nota.getTotal());
		}
		return textTotalNota;
	}

	public JSpinner getSpinnerQtde() {
		if (spinnerQtde == null) {
			spinnerQtde = new JSpinner();
			spinnerQtde.setBounds(352, 293, 75, 20);
		}
		return spinnerQtde;
	}

	public JTextField getTextNumeroNFE() {
		if (textNumeroNFE == null) {
			textNumeroNFE = new JTextField();
			textNumeroNFE.setDisabledTextColor(Color.BLACK);
			textNumeroNFE.setForeground(Color.BLACK);
			textNumeroNFE.setBackground(Color.LIGHT_GRAY);
			textNumeroNFE.setEnabled(false);
			textNumeroNFE.setHorizontalAlignment(SwingConstants.CENTER);
			textNumeroNFE.setBounds(30, 117, 141, 22);
			textNumeroNFE.setColumns(10);
			textNumeroNFE.setEditable(false);
			textNumeroNFE.setText(""+nota.getNumeroNota());
		}
		return textNumeroNFE;
	}

	public JLabel getLblNumNFE() {
		if (lblNumNFE == null) {
			lblNumNFE = new JLabel("N\u00FAmero da NFe");
			lblNumNFE.setBounds(30, 92, 141, 14);
		}
		return lblNumNFE;
	}

	public JFormattedTextField getTextDataEmissao() {
		if (txtDataEmissao == null) {
			try {
				txtDataEmissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
				txtDataEmissao.setHorizontalAlignment(SwingConstants.CENTER);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtDataEmissao.setValue(null);
			txtDataEmissao.setBounds(310, 224, 149, 20);
			txtDataEmissao.setColumns(10);
			txtDataEmissao.setEditable(true);
			txtDataEmissao.setText(nota.getDataCompra());
		}
		return txtDataEmissao;
	}

	public JLabel getLblDataEmissao() {
		if (lblDataEmissao == null) {
			lblDataEmissao = new JLabel("Data de Emissão da NFe");
			lblDataEmissao.setBounds(313, 207, 165, 14);
		}
		return lblDataEmissao;
	}

	private JLabel getLblCNPJ() {
		if (lblCNPJ == null) {
			lblCNPJ = new JLabel("CNPJ Fornecedor");
			lblCNPJ.setBounds(30, 150, 141, 14);
		}
		return lblCNPJ;
	}

	public JTextField getTextFieldCNPJ() {
		if (textFieldCNPJ == null) {
			textFieldCNPJ = new JTextField();
			textFieldCNPJ.setDisabledTextColor(Color.BLACK);
			textFieldCNPJ.setForeground(Color.BLACK);
			textFieldCNPJ.setBackground(Color.LIGHT_GRAY);
			textFieldCNPJ.setEnabled(false);
			textFieldCNPJ.setEditable(false);
			textFieldCNPJ.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldCNPJ.setBounds(30, 175, 141, 20);
			textFieldCNPJ.setColumns(10);
			textFieldCNPJ.setText(nota.getCnpj());
		}
		return textFieldCNPJ;
	}

	private JLabel getLblNomeFornec() {
		if (lblNomeFornec == null) {
			lblNomeFornec = new JLabel("Nome do Fornecedor");
			lblNomeFornec.setBounds(30, 207, 141, 14);
		}
		return lblNomeFornec;
	}

	public JTextField getTextFieldNomeFornec() {
		if (textFieldNomeFornec == null) {
			textFieldNomeFornec = new JTextField();
			textFieldNomeFornec.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldNomeFornec.setBounds(30, 224, 261, 20);
			textFieldNomeFornec.setColumns(10);
			textFieldNomeFornec.setText(nota.getFornecedor());
		}
		return textFieldNomeFornec;
	}

	private JLabel getLblValorTotalProd() {
		if (lblValorTotalProd == null) {
			lblValorTotalProd = new JLabel("Valor Total dos Produtos (R$)");
			lblValorTotalProd.setBounds(474, 150, 166, 14);
		}
		return lblValorTotalProd;
	}

	public JTextField getTextFieldVTotalProd() {
		if (textFieldVTotalProd == null) {
			textFieldVTotalProd = new JTextField();
			textFieldVTotalProd.setBackground(Color.LIGHT_GRAY);
			textFieldVTotalProd.setDisabledTextColor(Color.BLACK);
			textFieldVTotalProd.setEnabled(false);
			textFieldVTotalProd.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldVTotalProd.setBounds(474, 176, 149, 20);
			textFieldVTotalProd.setText(nota.getTotal()-nota.getOutros()+"");
			textFieldVTotalProd.setColumns(10);
			textFieldVTotalProd.setEditable(false);
		}
		return textFieldVTotalProd;
	}

	public JTextField getTextFieldOutrosCustos() {
		if (textFieldOutrosCustos == null) {
			textFieldOutrosCustos = new JTextField();
			textFieldOutrosCustos.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldOutrosCustos.setBounds(474, 118, 149, 20);
			textFieldOutrosCustos.setColumns(10);
			textFieldOutrosCustos.setEditable(true);
			textFieldOutrosCustos.setText(""+nota.getOutros());
		}
		return textFieldOutrosCustos;
	}

	private JLabel getLblOutrosCustos() {
		if (lblOutrosCustos == null) {
			lblOutrosCustos = new JLabel("Valor Total Outros Custos (R$)");
			lblOutrosCustos.setBounds(474, 92, 176, 14);
		}
		return lblOutrosCustos;
	}

	private JLabel getLblChave() {
		if (lblChave == null) {
			lblChave = new JLabel("Chave de Acesso da NFe");
			lblChave.setBounds(183, 92, 231, 14);
		}
		return lblChave;
	}

	public JTextField getTextFieldChaveNFE() {
		if (textFieldChaveNFE == null) {
			textFieldChaveNFE = new JTextField();
			textFieldChaveNFE.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldChaveNFE.setBounds(183, 118, 276, 20);
			textFieldChaveNFE.setColumns(10);
			textFieldChaveNFE.setText(nota.getChaveAcesso());
		}
		return textFieldChaveNFE;
	}

	public JLabel getBtnAddProduto() {
		if (lblAddProduto == null) {
			lblAddProduto = new JLabel("");
			lblAddProduto.setBounds(577, 282, 63, 39);
			lblAddProduto.setToolTipText("Adicionar Produto a Lista");
			lblAddProduto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddProduto.setIcon(new ImageIcon("Interno/add.png"));
		}
		return lblAddProduto;
	}

	public JComboBox<Object> getComboBoxProduto() {
		if (comboBoxProduto == null) {
			String produtos[] = produtoDAO.buscarNomeeId();
			comboBoxProduto = new JComboBox<Object>(produtos);
			comboBoxProduto.setBounds(30, 293, 312, 20);
		}
		return comboBoxProduto;
	}
	
	
	private JLabel getLblidCadastro() {
		if (lblidCadastro == null) {
			lblidCadastro = new JLabel("C\u00F3digo de Cadastro da NFe");
			lblidCadastro.setBounds(30, 29, 212, 14);
		}
		return lblidCadastro;
	}
	
	public JTextField getTextFieldDataCadastro() {
		if (textFieldDataCadastro == null) {
			textFieldDataCadastro = new JTextField();
			textFieldDataCadastro.setBackground(Color.LIGHT_GRAY);
			textFieldDataCadastro.setDisabledTextColor(Color.BLACK);
			textFieldDataCadastro.setEnabled(false);
			textFieldDataCadastro.setEditable(false);
			textFieldDataCadastro.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldDataCadastro.setBounds(474, 54, 149, 20);
			textFieldDataCadastro.setColumns(10);
			textFieldDataCadastro.setText(nota.getDataEntrada()+"");
		}
		return textFieldDataCadastro;
	}
	
	
	public JTextField getTextFieldCodigoNFe() {
		if (textFieldCodigoNFe == null) {
			textFieldCodigoNFe = new JTextField();
			textFieldCodigoNFe.setBackground(Color.LIGHT_GRAY);
			textFieldCodigoNFe.setDisabledTextColor(Color.BLACK);
			textFieldCodigoNFe.setForeground(Color.BLACK);
			textFieldCodigoNFe.setEnabled(false);
			textFieldCodigoNFe.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldCodigoNFe.setEditable(false);
			textFieldCodigoNFe.setBounds(30, 54, 141, 20);
			textFieldCodigoNFe.setColumns(10);
			textFieldCodigoNFe.setText(""+nota.getIdCompra());
		}
		return textFieldCodigoNFe;
	}
}
