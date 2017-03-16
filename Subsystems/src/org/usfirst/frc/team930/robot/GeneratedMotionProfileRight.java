package org.usfirst.frc.team930.robot;

public class GeneratedMotionProfileRight {	
	public static final int kNumPointsToHopper =356; // 556
	public static final int kNumPointsWait =200;
	//public static final int kNumPointsBackwards =240;
	public static final int kNumPointsBackwards =796;
	//public static final int kNumPointsToBoiler =242;
	public static final int kNumPointsToBoiler =1038;
	public static final int kNumPoints =1038; // 242
	// Position (rotations)	Velocity (RPM)	Duration (ms)
	public static double [][]Points = new double[][]{ 
		/*
		{0.00003,	0.3	,10},
		{0.0001,	0.42	,10},
		{0.00032,	1.32	,10},
		{0.00073,	2.4	,10},
		{0.00135,	3.72	,10},
		{0.00225,	5.4	,10},
		{0.00347,	7.32	,10},
		{0.00507,	9.6	,10},
		{0.00707,	12	,10},
		{0.00947,	14.4	,10},
		{0.01227,	16.8	,10},
		{0.01547,	19.2	,10},
		{0.01907,	21.6	,10},
		{0.02308,	24	,10},
		{0.02748,	26.4	,10},
		{0.03228,	28.8	,10},
		{0.03748,	31.2	,10},
		{0.04308,	33.6	,10},
		{0.04908,	36	,10},
		{0.05548,	38.4	,10},
		{0.06228,	40.8	,10},
		{0.06948,	43.2	,10},
		{0.07708,	45.6	,10},
		{0.08508,	48	,10},
		{0.09348,	50.4	,10},
		{0.10228,	52.8	,10},
		{0.11148,	55.2	,10},
		{0.12108,	57.6	,10},
		{0.13108,	60	,10},
		{0.14148,	62.4	,10},
		{0.15228,	64.8	,10},
		{0.16348,	67.2	,10},
		{0.17507,	69.6	,10},
		{0.18707,	72	,10},
		{0.19947,	74.4	,10},
		{0.21227,	76.8	,10},
		{0.22547,	79.2	,10},
		{0.23907,	81.6	,10},
		{0.25307,	84	,10},
		{0.26747,	86.4	,10},
		{0.28227,	88.8	,10},
		{0.29747,	91.2	,10},
		{0.31307,	93.6	,10},
		{0.32907,	96	,10},
		{0.34547,	98.4	,10},
		{0.36227,	100.8	,10},
		{0.37947,	103.2	,10},
		{0.39707,	105.6	,10},
		{0.41507,	108	,10},
		{0.43348,	110.4	,10},
		{0.45228,	112.8	,10},
		{0.47148,	115.2	,10},
		{0.49108,	117.6	,10},
		{0.51108,	120	,10},
		{0.53148,	122.4	,10},
		{0.55228,	124.8	,10},
		{0.57348,	127.2	,10},
		{0.59508,	129.6	,10},
		{0.61708,	132	,10},
		{0.63948,	134.4	,10},
		{0.66228,	136.8	,10},
		{0.68548,	139.2	,10},
		{0.70907,	141.6	,10},
		{0.73307,	144	,10},
		{0.75747,	146.4	,10},
		{0.78227,	148.8	,10},
		{0.80747,	151.2	,10},
		{0.83307,	153.6	,10},
		{0.85907,	156	,10},
		{0.88547,	158.4	,10},
		{0.91227,	160.8	,10},
		{0.93947,	163.2	,10},
		{0.96707,	165.6	,10},
		{0.99507,	168	,10},
		{1.02347,	170.4	,10},
		{1.05227,	172.8	,10},
		{1.08147,	175.2	,10},
		{1.11107,	177.6	,10},
		{1.14107,	180	,10},
		{1.17147,	182.4	,10},
		{1.20227,	184.8	,10},
		{1.23347,	187.2	,10},
		{1.26507,	189.6	,10},
		{1.29707,	192	,10},
		{1.32947,	194.4	,10},
		{1.36227,	196.8	,10},
		{1.39547,	199.2	,10},
		{1.42907,	201.6	,10},
		{1.46307,	204	,10},
		{1.49747,	206.4	,10},
		{1.53227,	208.8	,10},
		{1.56747,	211.2	,10},
		{1.60307,	213.6	,10},
		{1.63907,	216	,10},
		{1.67547,	218.4	,10},
		{1.71227,	220.8	,10},
		{1.74947,	223.2	,10},
		{1.78707,	225.6	,10},
		{1.82507,	228	,10},
		{1.86347,	230.4	,10},
		{1.90225,	232.62	,10},
		{1.94135,	234.6	,10},
		{1.98072,	236.22	,10},
		{2.02032,	237.6	,10},
		{2.0601,	238.62	,10},
		{2.1,	239.4	,10},
		{2.13997,	239.82	,10},
		{2.17997,	240	,10},
		{2.21997,	240	,10},
		{2.25997,	240	,10},
		{2.29997,	240	,10},
		{2.33997,	240	,10},
		{2.37997,	240	,10},
		{2.41997,	240	,10},
		{2.45997,	240	,10},
		{2.49997,	240	,10},
		{2.53997,	240	,10},
		{2.57997,	240	,10},
		{2.61997,	240	,10},
		{2.65997,	240	,10},
		{2.69997,	240	,10},
		{2.73997,	240	,10},
		{2.77997,	240	,10},
		{2.81997,	240	,10},
		{2.85997,	240	,10},
		{2.89995,	239.88	,10},
		{2.93985,	239.4	,10},
		{2.97963,	238.68	,10},
		{3.01923,	237.6	,10},
		{3.0586,	236.28	,10},
		{3.0977,	234.6	,10},
		{3.13648,	232.68	,10},
		{3.17488,	230.4	,10},
		{3.21288,	228	,10},
		{3.25048,	225.6	,10},
		{3.28768,	223.2	,10},
		{3.32448,	220.8	,10},
		{3.36088,	218.4	,10},
		{3.39688,	216	,10},
		{3.43248,	213.6	,10},
		{3.46768,	211.2	,10},
		{3.50248,	208.8	,10},
		{3.53688,	206.4	,10},
		{3.57088,	204	,10},
		{3.60448,	201.6	,10},
		{3.63768,	199.2	,10},
		{3.67048,	196.8	,10},
		{3.70288,	194.4	,10},
		{3.73489,	192	,10},
		{3.76649,	189.6	,10},
		{3.79769,	187.2	,10},
		{3.82849,	184.8	,10},
		{3.85889,	182.4	,10},
		{3.88889,	180	,10},
		{3.91849,	177.6	,10},
		{3.94769,	175.2	,10},
		{3.97649,	172.8	,10},
		{4.00489,	170.4	,10},
		{4.03289,	168	,10},
		{4.06049,	165.6	,10},
		{4.08769,	163.2	,10},
		{4.11449,	160.8	,10},
		{4.14089,	158.4	,10},
		{4.16689,	156	,10},
		{4.19249,	153.6	,10},
		{4.21769,	151.2	,10},
		{4.24249,	148.8	,10},
		{4.26689,	146.4	,10},
		{4.2909,	144	,10},
		{4.3145,	141.6	,10},
		{4.3377,	139.2	,10},
		{4.3605,	136.8	,10},
		{4.3829,	134.4	,10},
		{4.4049,	132	,10},
		{4.4265,	129.6	,10},
		{4.4477,	127.2	,10},
		{4.4685,	124.8	,10},
		{4.4889,	122.4	,10},
		{4.5089,	120	,10},
		{4.5285,	117.6	,10},
		{4.5477,	115.2	,10},
		{4.5665,	112.8	,10},
		{4.5849,	110.4	,10},
		{4.6029,	108	,10},
		{4.6205,	105.6	,10},
		{4.6377,	103.2	,10},
		{4.6545,	100.8	,10},
		{4.6709,	98.4	,10},
		{4.68691,	96	,10},
		{4.70251,	93.6	,10},
		{4.71771,	91.2	,10},
		{4.73251,	88.8	,10},
		{4.74691,	86.4	,10},
		{4.76091,	84	,10},
		{4.77451,	81.6	,10},
		{4.78771,	79.2	,10},
		{4.80051,	76.8	,10},
		{4.81291,	74.4	,10},
		{4.82491,	72	,10},
		{4.83651,	69.6	,10},
		{4.84771,	67.2	,10},
		{4.85851,	64.8	,10},
		{4.86891,	62.4	,10},
		{4.87891,	60	,10},
		{4.88851,	57.6	,10},
		{4.89771,	55.2	,10},
		{4.90651,	52.8	,10},
		{4.91491,	50.4	,10},
		{4.92292,	48	,10},
		{4.93052,	45.6	,10},
		{4.93772,	43.2	,10},
		{4.94452,	40.8	,10},
		{4.95092,	38.4	,10},
		{4.95692,	36	,10},
		{4.96252,	33.6	,10},
		{4.96772,	31.2	,10},
		{4.97252,	28.8	,10},
		{4.97692,	26.4	,10},
		{4.98092,	24	,10},
		{4.98452,	21.6	,10},
		{4.98772,	19.2	,10},
		{4.99052,	16.8	,10},
		{4.99292,	14.4	,10},
		{4.99492,	12	,10},
		{4.99652,	9.6	,10},
		{4.99775,	7.38	,10},
		{4.99865,	5.4	,10},
		{4.99927,	3.78	,10},
		{4.99967,	2.4	,10},
		{4.9999,	1.38	,10},
		{5,	0.6	,10},
		{5.00002,	0.18	,10},
		{5.00002,	0	,10},
		{5.00002,	0	,10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10}
*/
		// Drive to Hopper
		{0.00003,	0.3	,10},
		{0.00004,	0.06	,10},
		{0.00023,	1.2	,10},
		{0.00058,	2.1	,10},
		{0.00113,	3.3	,10},
		{0.00193,	4.74	,10},
		{0.003,	6.48	,10},
		{0.00441,	8.46	,10},
		{0.00617,	10.56	,10},
		{0.00828,	12.66	,10},
		{0.01075,	14.76	,10},
		{0.01356,	16.86	,10},
		{0.01673,	19.02	,10},
		{0.02024,	21.12	,10},
		{0.02411,	23.22	,10},
		{0.02833,	25.32	,10},
		{0.0329,	27.42	,10},
		{0.03782,	29.52	,10},
		{0.04309,	31.62	,10},
		{0.04871,	33.72	,10},
		{0.05468,	35.82	,10},
		{0.061,	37.92	,10},
		{0.06766,	40.02	,10},
		{0.07468,	42.12	,10},
		{0.08205,	44.22	,10},
		{0.08976,	46.26	,10},
		{0.09782,	48.36	,10},
		{0.10623,	50.46	,10},
		{0.11498,	52.5	,10},
		{0.12408,	54.6	,10},
		{0.13353,	56.7	,10},
		{0.14332,	58.74	,10},
		{0.15346,	60.84	,10},
		{0.16394,	62.88	,10},
		{0.17477,	64.98	,10},
		{0.18593,	67.02	,10},
		{0.19744,	69.06	,10},
		{0.2093,	71.1	,10},
		{0.22149,	73.14	,10},
		{0.23403,	75.18	,10},
		{0.2469,	77.22	,10},
		{0.26011,	79.26	,10},
		{0.27367,	81.3	,10},
		{0.28756,	83.34	,10},
		{0.30178,	85.38	,10},
		{0.31635,	87.36	,10},
		{0.33124,	89.4	,10},
		{0.34647,	91.38	,10},
		{0.36204,	93.42	,10},
		{0.37794,	95.4	,10},
		{0.39417,	97.38	,10},
		{0.41073,	99.36	,10},
		{0.42762,	101.34	,10},
		{0.44483,	103.32	,10},
		{0.46238,	105.24	,10},
		{0.48025,	107.22	,10},
		{0.49844,	109.2	,10},
		{0.51696,	111.12	,10},
		{0.5358,	113.04	,10},
		{0.55496,	114.96	,10},
		{0.57445,	116.88	,10},
		{0.59425,	118.8	,10},
		{0.61436,	120.72	,10},
		{0.6348,	122.58	,10},
		{0.65554,	124.5	,10},
		{0.6766,	126.36	,10},
		{0.69797,	128.22	,10},
		{0.71965,	130.08	,10},
		{0.74163,	131.94	,10},
		{0.76393,	133.74	,10},
		{0.78652,	135.6	,10},
		{0.80942,	137.4	,10},
		{0.83262,	139.2	,10},
		{0.85611,	141	,10},
		{0.87991,	142.74	,10},
		{0.90399,	144.54	,10},
		{0.92837,	146.28	,10},
		{0.95304,	148.02	,10},
		{0.978,	149.76	,10},
		{1.00324,	151.44	,10},
		{1.02877,	153.18	,10},
		{1.05458,	154.86	,10},
		{1.08066,	156.54	,10},
		{1.10702,	158.16	,10},
		{1.13366,	159.84	,10},
		{1.16056,	161.46	,10},
		{1.18774,	163.02	,10},
		{1.21518,	164.64	,10},
		{1.24288,	166.2	,10},
		{1.27084,	167.76	,10},
		{1.29906,	169.32	,10},
		{1.32754,	170.82	,10},
		{1.35626,	172.32	,10},
		{1.38523,	173.82	,10},
		{1.41445,	175.32	,10},
		{1.44391,	176.76	,10},
		{1.47361,	178.2	,10},
		{1.50354,	179.58	,10},
		{1.53371,	181.02	,10},
		{1.56411,	182.34	,10},
		{1.59471,	183.6	,10},
		{1.62547,	184.56	,10},
		{1.65636,	185.34	,10},
		{1.68732,	185.82	,10},
		{1.71833,	186	,10},
		{1.74933,	186	,10},
		{1.78029,	185.76	,10},
		{1.81117,	185.28	,10},
		{1.84195,	184.68	,10},
		{1.87263,	184.08	,10},
		{1.90321,	183.48	,10},
		{1.93369,	182.88	,10},
		{1.96407,	182.28	,10},
		{1.99435,	181.68	,10},
		{2.02452,	181.08	,10},
		{2.0546,	180.48	,10},
		{2.08458,	179.88	,10},
		{2.11447,	179.28	,10},
		{2.14425,	178.68	,10},
		{2.17394,	178.14	,10},
		{2.20353,	177.54	,10},
		{2.23303,	177	,10},
		{2.26243,	176.4	,10},
		{2.29174,	175.86	,10},
		{2.32096,	175.32	,10},
		{2.35008,	174.78	,10},
		{2.37912,	174.24	,10},
		{2.40808,	173.7	,10},
		{2.43695,	173.22	,10},
		{2.46573,	172.74	,10},
		{2.49444,	172.2	,10},
		{2.52306,	171.78	,10},
		{2.55161,	171.3	,10},
		{2.58009,	170.88	,10},
		{2.60849,	170.4	,10},
		{2.63683,	170.04	,10},
		{2.6651,	169.62	,10},
		{2.69331,	169.26	,10},
		{2.72145,	168.9	,10},
		{2.74955,	168.54	,10},
		{2.77758,	168.24	,10},
		{2.80557,	167.94	,10},
		{2.83351,	167.64	,10},
		{2.86141,	167.4	,10},
		{2.88927,	167.16	,10},
		{2.91709,	166.92	,10},
		{2.94488,	166.74	,10},
		{2.97264,	166.56	,10},
		{3.00038,	166.44	,10},
		{3.0281,	166.32	,10},
		{3.0558,	166.2	,10},
		{3.08349,	166.14	,10},
		{3.11117,	166.08	,10},
		{3.13884,	166.08	,10},
		{3.16652,	166.08	,10},
		{3.1942,	166.08	,10},
		{3.22189,	166.14	,10},
		{3.24959,	166.2	,10},
		{3.27731,	166.32	,10},
		{3.30505,	166.44	,10},
		{3.33281,	166.56	,10},
		{3.3606,	166.74	,10},
		{3.38842,	166.92	,10},
		{3.41628,	167.16	,10},
		{3.44418,	167.4	,10},
		{3.47212,	167.64	,10},
		{3.50011,	167.94	,10},
		{3.52815,	168.24	,10},
		{3.55625,	168.54	,10},
		{3.5844,	168.9	,10},
		{3.61261,	169.26	,10},
		{3.64089,	169.68	,10},
		{3.66923,	170.04	,10},
		{3.69765,	170.46	,10},
		{3.72613,	170.94	,10},
		{3.75469,	171.36	,10},
		{3.78333,	171.84	,10},
		{3.81205,	172.32	,10},
		{3.84085,	172.8	,10},
		{3.86973,	173.28	,10},
		{3.8987,	173.82	,10},
		{3.92776,	174.36	,10},
		{3.95691,	174.9	,10},
		{3.98616,	175.44	,10},
		{4.01549,	176.04	,10},
		{4.04492,	176.58	,10},
		{4.07445,	177.18	,10},
		{4.10407,	177.72	,10},
		{4.13379,	178.32	,10},
		{4.16362,	178.92	,10},
		{4.19354,	179.52	,10},
		{4.22356,	180.12	,10},
		{4.25369,	180.72	,10},
		{4.28391,	181.38	,10},
		{4.31424,	181.98	,10},
		{4.34468,	182.58	,10},
		{4.37521,	183.24	,10},
		{4.40585,	183.84	,10},
		{4.43659,	184.44	,10},
		{4.46744,	185.1	,10},
		{4.49839,	185.7	,10},
		{4.52944,	186.3	,10},
		{4.5606,	186.96	,10},
		{4.59186,	187.56	,10},
		{4.62322,	188.16	,10},
		{4.65468,	188.76	,10},
		{4.68625,	189.42	,10},
		{4.71791,	190.02	,10},
		{4.74968,	190.62	,10},
		{4.78155,	191.22	,10},
		{4.81351,	191.82	,10},
		{4.84558,	192.42	,10},
		{4.87774,	192.96	,10},
		{4.91,	193.56	,10},
		{4.94236,	194.16	,10},
		{4.97481,	194.7	,10},
		{5.00736,	195.3	,10},
		{5.04,	195.84	,10},
		{5.07274,	196.38	,10},
		{5.10556,	196.98	,10},
		{5.13848,	197.52	,10},
		{5.17149,	198.06	,10},
		{5.20459,	198.6	,10},
		{5.23777,	199.14	,10},
		{5.27104,	199.62	,10},
		{5.3044,	200.16	,10},
		{5.33785,	200.7	,10},
		{5.37138,	201.18	,10},
		{5.40499,	201.66	,10},
		{5.43869,	202.2	,10},
		{5.47246,	202.68	,10},
		{5.50632,	203.16	,10},
		{5.54026,	203.64	,10},
		{5.57427,	204.12	,10},
		{5.60837,	204.54	,10},
		{5.64254,	205.02	,10},
		{5.67678,	205.44	,10},
		{5.7111,	205.92	,10},
		{5.74549,	206.34	,10},
		{5.77996,	206.76	,10},
		{5.81447,	207.06	,10},
		{5.84898,	207.06	,10},
		{5.88345,	206.82	,10},
		{5.91784,	206.34	,10},
		{5.9521,	205.5	,10},
		{5.98617,	204.48	,10},
		{6.02002,	203.1	,10},
		{6.05361,	201.48	,10},
		{6.0869,	199.74	,10},
		{6.1199,	198	,10},
		{6.1526,	196.2	,10},
		{6.185,	194.4	,10},
		{6.2171,	192.6	,10},
		{6.2489,	190.8	,10},
		{6.28038,	188.94	,10},
		{6.31156,	187.08	,10},
		{6.34243,	185.22	,10},
		{6.37299,	183.36	,10},
		{6.40323,	181.44	,10},
		{6.43316,	179.58	,10},
		{6.46277,	177.66	,10},
		{6.49206,	175.74	,10},
		{6.52102,	173.82	,10},
		{6.54966,	171.84	,10},
		{6.57798,	169.92	,10},
		{6.60597,	167.94	,10},
		{6.63363,	165.96	,10},
		{6.66096,	163.98	,10},
		{6.68796,	162	,10},
		{6.71463,	160.02	,10},
		{6.74096,	157.98	,10},
		{6.76696,	156	,10},
		{6.79262,	153.96	,10},
		{6.81794,	151.92	,10},
		{6.84292,	149.88	,10},
		{6.86756,	147.84	,10},
		{6.89186,	145.8	,10},
		{6.91582,	143.76	,10},
		{6.93943,	141.66	,10},
		{6.9627,	139.62	,10},
		{6.98562,	137.52	,10},
		{7.0082,	135.42	,10},
		{7.03042,	133.38	,10},
		{7.0523,	131.28	,10},
		{7.07383,	129.18	,10},
		{7.09501,	127.08	,10},
		{7.11583,	124.98	,10},
		{7.1363,	122.82	,10},
		{7.15642,	120.72	,10},
		{7.17619,	118.62	,10},
		{7.1956,	116.46	,10},
		{7.21466,	114.36	,10},
		{7.23336,	112.2	,10},
		{7.2517,	110.04	,10},
		{7.26968,	107.88	,10},
		{7.28731,	105.78	,10},
		{7.30458,	103.62	,10},
		{7.32149,	101.46	,10},
		{7.33803,	99.3	,10},
		{7.35422,	97.14	,10},
		{7.37005,	94.98	,10},
		{7.38551,	92.76	,10},
		{7.40061,	90.6	,10},
		{7.41535,	88.44	,10},
		{7.42973,	86.28	,10},
		{7.44374,	84.06	,10},
		{7.45739,	81.9	,10},
		{7.47067,	79.68	,10},
		{7.48359,	77.52	,10},
		{7.49615,	75.3	,10},
		{7.50833,	73.14	,10},
		{7.52016,	70.92	,10},
		{7.53161,	68.7	,10},
		{7.5427,	66.54	,10},
		{7.55342,	64.32	,10},
		{7.56377,	62.1	,10},
		{7.57376,	59.94	,10},
		{7.58338,	57.72	,10},
		{7.59262,	55.5	,10},
		{7.6015,	53.28	,10},
		{7.61002,	51.06	,10},
		{7.61816,	48.84	,10},
		{7.62593,	46.62	,10},
		{7.63333,	44.4	,10},
		{7.64037,	42.18	,10},
		{7.64703,	39.96	,10},
		{7.65332,	37.74	,10},
		{7.65924,	35.52	,10},
		{7.66479,	33.3	,10},
		{7.66998,	31.08	,10},
		{7.67479,	28.86	,10},
		{7.67922,	26.64	,10},
		{7.68329,	24.42	,10},
		{7.68699,	22.2	,10},
		{7.69031,	19.92	,10},
		{7.69327,	17.7	,10},
		{7.69585,	15.48	,10},
		{7.69806,	13.26	,10},
		{7.6999,	11.04	,10},
		{7.70136,	8.82	,10},
		{7.70249,	6.72	,10},
		{7.70331,	4.92	,10},
		{7.70388,	3.42	,10},
		{7.70424,	2.16	,10},
		{7.70444,	1.2	,10},
		{7.70453,	0.54	,10},
		{7.70456,	0.12	,10},
		{7.70456,	0	,10},
	
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		
		// Wait 2 sec
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
	
	// Backup and Pivot
		{-0.00003,	-0.3	,10},
		{-0.00003,	-0.06	,10},
		{-0.00005,	-0.12	,10},
		{-0.00008,	-0.18	,10},
		{-0.00013,	-0.3	,10},
		{-0.0002,	-0.42	,10},
		{-0.0003,	-0.6	,10},
		{-0.00044,	-0.78	,10},
		{-0.00061,	-1.02	,10},
		{-0.00082,	-1.26	,10},
		{-0.00108,	-1.56	,10},
		{-0.00139,	-1.86	,10},
		{-0.00175,	-2.16	,10},
		{-0.00218,	-2.52	,10},
		{-0.00267,	-2.94	,10},
		{-0.00323,	-3.36	,10},
		{-0.00388,	-3.9	,10},
		{-0.00462,	-4.44	,10},
		{-0.00545,	-4.98	,10},
		{-0.00639,	-5.64	,10},
		{-0.00745,	-6.36	,10},
		{-0.00863,	-7.14	,10},
		{-0.00996,	-7.92	,10},
		{-0.01143,	-8.82	,10},
		{-0.01306,	-9.78	,10},
		{-0.01487,	-10.86	,10},
		{-0.01686,	-11.94	,10},
		{-0.01905,	-13.14	,10},
		{-0.02146,	-14.4	,10},
		{-0.02408,	-15.78	,10},
		{-0.02695,	-17.16	,10},
		{-0.03006,	-18.72	,10},
		{-0.03345,	-20.28	,10},
		{-0.03711,	-21.96	,10},
		{-0.04106,	-23.76	,10},
		{-0.04533,	-25.56	,10},
		{-0.04992,	-27.54	,10},
		{-0.05484,	-29.52	,10},
		{-0.06011,	-31.62	,10},
		{-0.06575,	-33.84	,10},
		{-0.07177,	-36.12	,10},
		{-0.07817,	-38.46	,10},
		{-0.08499,	-40.86	,10},
		{-0.09222,	-43.38	,10},
		{-0.09988,	-45.96	,10},
		{-0.10798,	-48.6	,10},
		{-0.11654,	-51.36	,10},
		{-0.12557,	-54.18	,10},
		{-0.13507,	-57	,10},
		{-0.14507,	-59.94	,10},
		{-0.15556,	-63	,10},
		{-0.16657,	-66.06	,10},
		{-0.1781,	-69.18	,10},
		{-0.19015,	-72.36	,10},
		{-0.20275,	-75.6	,10},
		{-0.2159,	-78.9	,10},
		{-0.2296,	-82.26	,10},
		{-0.24388,	-85.62	,10},
		{-0.25872,	-89.1	,10},
		{-0.27415,	-92.58	,10},
		{-0.29017,	-96.12	,10},
		{-0.30679,	-99.72	,10},
		{-0.32401,	-103.32	,10},
		{-0.34185,	-106.98	,10},
		{-0.3603,	-110.7	,10},
		{-0.37938,	-114.48	,10},
		{-0.39909,	-118.26	,10},
		{-0.41944,	-122.1	,10},
		{-0.44043,	-126	,10},
		{-0.46208,	-129.9	,10},
		{-0.48439,	-133.86	,10},
		{-0.50736,	-137.82	,10},
		{-0.53101,	-141.9	,10},
		{-0.55534,	-145.98	,10},
		{-0.58036,	-150.12	,10},
		{-0.60607,	-154.26	,10},
		{-0.63249,	-158.52	,10},
		{-0.65962,	-162.78	,10},
		{-0.68748,	-167.1	,10},
		{-0.71607,	-171.54	,10},
		{-0.7454,	-175.98	,10},
		{-0.77548,	-180.48	,10},
		{-0.80633,	-185.1	,10},
		{-0.83796,	-189.78	,10},
		{-0.87038,	-194.52	,10},
		{-0.90361,	-199.38	,10},
		{-0.93766,	-204.3	,10},
		{-0.97255,	-209.34	,10},
		{-1.0083,	-214.5	,10},
		{-1.04493,	-219.78	,10},
		{-1.08247,	-225.18	,10},
		{-1.12093,	-230.76	,10},
		{-1.16034,	-236.46	,10},
		{-1.20074,	-242.4	,10},
		{-1.24215,	-248.46	,10},
		{-1.28461,	-254.76	,10},
		{-1.32816,	-261.3	,10},
		{-1.37284,	-268.08	,10},
		{-1.41869,	-275.1	,10},
		{-1.46577,	-282.48	,10},
		{-1.5141,	-289.98	,10},
		{-1.56368,	-297.48	,10},
		{-1.6145,	-304.92	,10},
		{-1.66656,	-312.36	,10},
		{-1.71986,	-319.8	,10},
		{-1.77441,	-327.3	,10},
		{-1.8302,	-334.74	,10},
		{-1.88723,	-342.18	,10},
		{-1.94554,	-349.86	,10},
		{-2.0052,	-357.96	,10},
		{-2.0663,	-366.6	,10},
		{-2.12892,	-375.72	,10},
		{-2.19314,	-385.32	,10},
		{-2.25905,	-395.46	,10},
		{-2.32675,	-406.2	,10},
		{-2.39631,	-417.42	,10},
		{-2.46784,	-429.12	,10},
		{-2.54139,	-441.3	,10},
		{-2.61703,	-453.9	,10},
		{-2.69482,	-466.74	,10},
		{-2.77477,	-479.7	,10},
		{-2.85687,	-492.6	,10},
		{-2.94109,	-505.32	,10},
		{-3.02732,	-517.38	,10},
		{-3.11536,	-528.24	,10},
		{-3.2049,	-537.24	,10},
		{-3.29559,	-544.14	,10},
		{-3.38702,	-548.58	,10},
		{-3.47875,	-550.38	,10},
		{-3.57032,	-549.42	,10},
		{-3.66126,	-545.64	,10},
		{-3.75113,	-539.22	,10},
		{-3.83959,	-530.7	,10},
		{-3.92636,	-520.62	,10},
		{-4.01122,	-509.16	,10},
		{-4.09399,	-496.62	,10},
		{-4.17455,	-483.36	,10},
		{-4.25281,	-469.56	,10},
		{-4.32873,	-455.52	,10},
		{-4.40229,	-441.36	,10},
		{-4.47352,	-427.38	,10},
		{-4.54246,	-413.58	,10},
		{-4.60915,	-400.2	,10},
		{-4.67368,	-387.18	,10},
		{-4.73612,	-374.64	,10},
		{-4.79655,	-362.58	,10},
		{-4.85505,	-351.06	,10},
		{-4.91172,	-340.02	,10},
		{-4.96664,	-329.46	,10},
		{-5.01988,	-319.44	,10},
		{-5.07153,	-309.9	,10},
		{-5.12165,	-300.78	,10},
		{-5.17033,	-292.08	,10},
		{-5.21762,	-283.74	,10},
		{-5.26359,	-275.82	,10},
		{-5.3083,	-268.26	,10},
		{-5.3518,	-261	,10},
		{-5.39414,	-254.04	,10},
		{-5.43537,	-247.38	,10},
		{-5.47554,	-240.96	,10},
		{-5.51467,	-234.84	,10},
		{-5.55282,	-228.9	,10},
		{-5.59001,	-223.14	,10},
		{-5.62628,	-217.62	,10},
		{-5.66166,	-212.28	,10},
		{-5.69618,	-207.12	,10},
		{-5.72986,	-202.08	,10},
		{-5.76272,	-197.22	,10},
		{-5.7948,	-192.42	,10},
		{-5.8261,	-187.8	,10},
		{-5.85665,	-183.3	,10},
		{-5.88647,	-178.92	,10},
		{-5.91558,	-174.6	,10},
		{-5.94398,	-170.4	,10},
		{-5.9717,	-166.32	,10},
		{-5.99875,	-162.3	,10},
		{-6.02514,	-158.34	,10},
		{-6.05088,	-154.44	,10},
		{-6.07599,	-150.66	,10},
		{-6.10047,	-146.88	,10},
		{-6.12433,	-143.16	,10},
		{-6.14759,	-139.56	,10},
		{-6.17025,	-135.96	,10},
		{-6.19232,	-132.42	,10},
		{-6.21381,	-128.94	,10},
		{-6.23472,	-125.46	,10},
		{-6.25507,	-122.1	,10},
		{-6.27485,	-118.68	,10},
		{-6.29408,	-115.38	,10},
		{-6.31276,	-112.08	,10},
		{-6.33089,	-108.78	,10},
		{-6.34848,	-105.54	,10},
		{-6.36554,	-102.36	,10},
		{-6.38206,	-99.18	,10},
		{-6.39806,	-96	,10},
		{-6.41353,	-92.82	,10},
		{-6.42849,	-89.76	,10},
		{-6.44293,	-86.64	,10},
		{-6.45686,	-83.58	,10},
		{-6.47027,	-80.52	,10},
		{-6.48318,	-77.46	,10},
		{-6.49559,	-74.46	,10},
		{-6.50749,	-71.4	,10},
		{-6.5189,	-68.46	,10},
		{-6.52981,	-65.46	,10},
		{-6.54022,	-62.46	,10},
		{-6.55014,	-59.52	,10},
		{-6.55957,	-56.58	,10},
		{-6.56851,	-53.64	,10},
		{-6.57697,	-50.7	,10},
		{-6.58493,	-47.82	,10},
		{-6.59242,	-44.88	,10},
		{-6.59942,	-42	,10},
		{-6.60593,	-39.12	,10},
		{-6.61197,	-36.24	,10},
		{-6.61753,	-33.36	,10},
		{-6.6226,	-30.48	,10},
		{-6.6272,	-27.6	,10},
		{-6.63132,	-24.72	,10},
		{-6.63497,	-21.84	,10},
		{-6.63814,	-19.02	,10},
		{-6.64083,	-16.14	,10},
		{-6.64305,	-13.32	,10},
		{-6.6448,	-10.5	,10},
		{-6.64613,	-7.98	,10},
		{-6.64709,	-5.76	,10},
		{-6.64774,	-3.9	,10},
		{-6.64815,	-2.46	,10},
		{-6.64837,	-1.32	,10},
		{-6.64846,	-0.54	,10},
		{-6.64848,	-0.12	,10},
		{-6.64848,	-0	,10},

		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
	
	// Drive to Boiler
		{0.00003,	0.3	,10},
		{0.0001,	0.42	,10},
		{0.00032,	1.32	,10},
		{0.00073,	2.4	,10},
		{0.00135,	3.72	,10},
		{0.00225,	5.4	,10},
		{0.00347,	7.32	,10},
		{0.00507,	9.6	,10},
		{0.00707,	12	,10},
		{0.00947,	14.4	,10},
		{0.01227,	16.8	,10},
		{0.01547,	19.2	,10},
		{0.01907,	21.6	,10},
		{0.02308,	24	,10},
		{0.02748,	26.4	,10},
		{0.03228,	28.8	,10},
		{0.03748,	31.2	,10},
		{0.04308,	33.6	,10},
		{0.04908,	36	,10},
		{0.05548,	38.4	,10},
		{0.06228,	40.8	,10},
		{0.06948,	43.2	,10},
		{0.07708,	45.6	,10},
		{0.08508,	48	,10},
		{0.09348,	50.4	,10},
		{0.10228,	52.8	,10},
		{0.11148,	55.2	,10},
		{0.12108,	57.6	,10},
		{0.13108,	60	,10},
		{0.14148,	62.4	,10},
		{0.15228,	64.8	,10},
		{0.16348,	67.2	,10},
		{0.17507,	69.6	,10},
		{0.18707,	72	,10},
		{0.19947,	74.4	,10},
		{0.21227,	76.8	,10},
		{0.22547,	79.2	,10},
		{0.23907,	81.6	,10},
		{0.25307,	84	,10},
		{0.26747,	86.4	,10},
		{0.28227,	88.8	,10},
		{0.29747,	91.2	,10},
		{0.31307,	93.6	,10},
		{0.32907,	96	,10},
		{0.34547,	98.4	,10},
		{0.36227,	100.8	,10},
		{0.37947,	103.2	,10},
		{0.39707,	105.6	,10},
		{0.41507,	108	,10},
		{0.43348,	110.4	,10},
		{0.45228,	112.8	,10},
		{0.47148,	115.2	,10},
		{0.49108,	117.6	,10},
		{0.51108,	120	,10},
		{0.53148,	122.4	,10},
		{0.55228,	124.8	,10},
		{0.57348,	127.2	,10},
		{0.59508,	129.6	,10},
		{0.61708,	132	,10},
		{0.63948,	134.4	,10},
		{0.66228,	136.8	,10},
		{0.68548,	139.2	,10},
		{0.70907,	141.6	,10},
		{0.73307,	144	,10},
		{0.75747,	146.4	,10},
		{0.78227,	148.8	,10},
		{0.80747,	151.2	,10},
		{0.83307,	153.6	,10},
		{0.85907,	156	,10},
		{0.88547,	158.4	,10},
		{0.91227,	160.8	,10},
		{0.93947,	163.2	,10},
		{0.96707,	165.6	,10},
		{0.99507,	168	,10},
		{1.02347,	170.4	,10},
		{1.05227,	172.8	,10},
		{1.08147,	175.2	,10},
		{1.11107,	177.6	,10},
		{1.14107,	180	,10},
		{1.17147,	182.4	,10},
		{1.20227,	184.8	,10},
		{1.23347,	187.2	,10},
		{1.26507,	189.6	,10},
		{1.29707,	192	,10},
		{1.32947,	194.4	,10},
		{1.36227,	196.8	,10},
		{1.39547,	199.2	,10},
		{1.42907,	201.6	,10},
		{1.46307,	204	,10},
		{1.49747,	206.4	,10},
		{1.53227,	208.8	,10},
		{1.56747,	211.2	,10},
		{1.60307,	213.6	,10},
		{1.63907,	216	,10},
		{1.67547,	218.4	,10},
		{1.71227,	220.8	,10},
		{1.74947,	223.2	,10},
		{1.78707,	225.6	,10},
		{1.82507,	228	,10},
		{1.86347,	230.4	,10},
		{1.90225,	232.62	,10},
		{1.94135,	234.6	,10},
		{1.98072,	236.22	,10},
		{2.02032,	237.6	,10},
		{2.0601,	238.62	,10},
		{2.1,	239.4	,10},
		{2.13997,	239.82	,10},
		{2.17997,	240	,10},
		{2.21997,	240	,10},
		{2.25997,	240	,10},
		{2.29997,	240	,10},
		{2.33997,	240	,10},
		{2.37997,	240	,10},
		{2.41997,	240	,10},
		{2.45997,	240	,10},
		{2.49997,	240	,10},
		{2.53997,	240	,10},
		{2.57997,	240	,10},
		{2.61997,	240	,10},
		{2.65997,	240	,10},
		{2.69997,	240	,10},
		{2.73997,	240	,10},
		{2.77997,	240	,10},
		{2.81997,	240	,10},
		{2.85997,	240	,10},
		{2.89995,	239.88	,10},
		{2.93985,	239.4	,10},
		{2.97963,	238.68	,10},
		{3.01923,	237.6	,10},
		{3.0586,	236.28	,10},
		{3.0977,	234.6	,10},
		{3.13648,	232.68	,10},
		{3.17488,	230.4	,10},
		{3.21288,	228	,10},
		{3.25048,	225.6	,10},
		{3.28768,	223.2	,10},
		{3.32448,	220.8	,10},
		{3.36088,	218.4	,10},
		{3.39688,	216	,10},
		{3.43248,	213.6	,10},
		{3.46768,	211.2	,10},
		{3.50248,	208.8	,10},
		{3.53688,	206.4	,10},
		{3.57088,	204	,10},
		{3.60448,	201.6	,10},
		{3.63768,	199.2	,10},
		{3.67048,	196.8	,10},
		{3.70288,	194.4	,10},
		{3.73489,	192	,10},
		{3.76649,	189.6	,10},
		{3.79769,	187.2	,10},
		{3.82849,	184.8	,10},
		{3.85889,	182.4	,10},
		{3.88889,	180	,10},
		{3.91849,	177.6	,10},
		{3.94769,	175.2	,10},
		{3.97649,	172.8	,10},
		{4.00489,	170.4	,10},
		{4.03289,	168	,10},
		{4.06049,	165.6	,10},
		{4.08769,	163.2	,10},
		{4.11449,	160.8	,10},
		{4.14089,	158.4	,10},
		{4.16689,	156	,10},
		{4.19249,	153.6	,10},
		{4.21769,	151.2	,10},
		{4.24249,	148.8	,10},
		{4.26689,	146.4	,10},
		{4.2909,	144	,10},
		{4.3145,	141.6	,10},
		{4.3377,	139.2	,10},
		{4.3605,	136.8	,10},
		{4.3829,	134.4	,10},
		{4.4049,	132	,10},
		{4.4265,	129.6	,10},
		{4.4477,	127.2	,10},
		{4.4685,	124.8	,10},
		{4.4889,	122.4	,10},
		{4.5089,	120	,10},
		{4.5285,	117.6	,10},
		{4.5477,	115.2	,10},
		{4.5665,	112.8	,10},
		{4.5849,	110.4	,10},
		{4.6029,	108	,10},
		{4.6205,	105.6	,10},
		{4.6377,	103.2	,10},
		{4.6545,	100.8	,10},
		{4.6709,	98.4	,10},
		{4.68691,	96	,10},
		{4.70251,	93.6	,10},
		{4.71771,	91.2	,10},
		{4.73251,	88.8	,10},
		{4.74691,	86.4	,10},
		{4.76091,	84	,10},
		{4.77451,	81.6	,10},
		{4.78771,	79.2	,10},
		{4.80051,	76.8	,10},
		{4.81291,	74.4	,10},
		{4.82491,	72	,10},
		{4.83651,	69.6	,10},
		{4.84771,	67.2	,10},
		{4.85851,	64.8	,10},
		{4.86891,	62.4	,10},
		{4.87891,	60	,10},
		{4.88851,	57.6	,10},
		{4.89771,	55.2	,10},
		{4.90651,	52.8	,10},
		{4.91491,	50.4	,10},
		{4.92292,	48	,10},
		{4.93052,	45.6	,10},
		{4.93772,	43.2	,10},
		{4.94452,	40.8	,10},
		{4.95092,	38.4	,10},
		{4.95692,	36	,10},
		{4.96252,	33.6	,10},
		{4.96772,	31.2	,10},
		{4.97252,	28.8	,10},
		{4.97692,	26.4	,10},
		{4.98092,	24	,10},
		{4.98452,	21.6	,10},
		{4.98772,	19.2	,10},
		{4.99052,	16.8	,10},
		{4.99292,	14.4	,10},
		{4.99492,	12	,10},
		{4.99652,	9.6	,10},
		{4.99775,	7.38	,10},
		{4.99865,	5.4	,10},
		{4.99927,	3.78	,10},
		{4.99967,	2.4	,10},
		{4.9999,	1.38	,10},
		{5,	0.6	,10},
		{5.00002,	0.18	,10},
		{5.00002,	0	,10},
		{5.00002,	0	,10},

		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10},
		{0,	0,	10}
		
	};
}
