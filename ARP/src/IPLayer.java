import java.util.ArrayList;

public class IPLayer implements BaseLayer {
	public int nUpperLayerCount = 0;
	public String pLayerName = null;
	public BaseLayer p_UnderLayer = null;
	public ArrayList<BaseLayer> p_aUpperLayer = new ArrayList<BaseLayer>();
/*
typedef struct _IPLayer_HEADER {
unsigned char ip_verlen; // ip version ->IPv4 : 4 (1byte)
unsigned char ip_tos; // type of service (1byte)
unsigned short ip_len; // total packet length (2byte)
unsigned short ip_id; // datagram id (2byte)
unsigned short ip_fragoff; // fragment offset (2byte)
unsigned char ip_ttl; // time to live in gateway hops (1byte)
unsigned char ip_proto; // IP protocol (1byte)
unsigned short ip_cksum; // header checksum (2byte)
unsigned char ip_src[4]; // IP address of source (4byte)
unsigned char ip_dst[4]; // IP address of destination (4byte)
unsigned char ip_data[IP_DATA_SIZE]; // variable length data
} IPLayer_HEADER, *LPIPLayer_HEADER ;
*/
	private class _IP_ADDR {
		private byte[] addr = new byte[4];

		public _IP_ADDR() {
			this.addr[0] = (byte) 0x00;
			this.addr[1] = (byte) 0x00;
			this.addr[2] = (byte) 0x00;
			this.addr[3] = (byte) 0x00;
		}
	}

	private class _IP_HEADER {
		byte ip_verlen; // ip version ->IPv4 : 4 (1byte)
		byte ip_tos; // type of service (1byte)
		byte[] ip_len; // total packet length (2byte)
		byte[] ip_id; // datagram id (2byte)
		byte[] ip_fragoff; // fragment offset (2byte)
		byte ip_ttl; // time to live in gateway hops (1byte)
		byte ip_proto; // IP protocol (1byte)
		byte[] ip_cksum; // header checksum (2byte)
		_IP_ADDR ip_src; // IP address of source (4byte)
		_IP_ADDR ip_dst; // IP address of destination (4byte)
		byte[] ip_data; // variable length data


		public _IP_HEADER() {
			this.ip_src = new _IP_ADDR();
			this.ip_dst = new _IP_ADDR();
			this.ip_verlen = (byte) 4;
			this.ip_tos = (byte)0x00;
			this.ip_len = new byte[2];
			this.ip_id = new byte[2];
			this.ip_fragoff = new byte[2];
			this.ip_ttl = (byte)0x00;
			this.ip_proto = (byte)0x00;
			this.ip_cksum = new byte[2];

		}
	}

	_IP_HEADER m_sHeader = new _IP_HEADER();

	public IPLayer(String pName) {
		// super(pName);
		// TODO Auto-generated constructor stub
		pLayerName = pName;
		
	}


	public boolean Send(byte[] input, int length) {
	

		return false;
	}

	

	public boolean Receive(byte[] input) {
		
		return true;
	}

	@Override
	public void SetUnderLayer(BaseLayer pUnderLayer) {
		if (pUnderLayer == null)
			return;
		this.p_UnderLayer = pUnderLayer;
	}

	@Override
	public void SetUpperLayer(BaseLayer pUpperLayer) {
		if (pUpperLayer == null)
			return;
		this.p_aUpperLayer.add(nUpperLayerCount++, pUpperLayer);
	}

	@Override
	public String GetLayerName() {
		// TODO Auto-generated method stub
		return pLayerName;
	}

	@Override
	public BaseLayer GetUnderLayer() {
		if (p_UnderLayer == null)
			return null;
		return p_UnderLayer;
	}

	@Override
	public BaseLayer GetUpperLayer(int nindex) {
		if (nindex < 0 || nindex > nUpperLayerCount || nUpperLayerCount < 0)
			return null;
		return p_aUpperLayer.get(nindex);
	}

	@Override
	public void SetUpperUnderLayer(BaseLayer pUULayer) {
		this.SetUpperLayer(pUULayer);
		pUULayer.SetUnderLayer(this);
	}
}
