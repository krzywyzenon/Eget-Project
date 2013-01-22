package graphics;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import alleycat.Battle;
import alleycat.CatCharacter;
import alleycat.Enemy;
import alleycat.MainCharacter;
import alleycat.Room;
//Uwaga - zrobic switcha ktory zmienia ikone miski w zaleznosci od tego czy pelna czy pusta - zmienia element a arrayu
public class FirstRoom {
	MainCharacter ourHero;
	Enemy evilBat;
	Room startRoom;
	JLabel labels[] = new JLabel[12];
	JFrame myFrame = null;
	ImageIcon floor[] = new ImageIcon[17];
	FRanim animations = null;
	JButton buttons[] = new JButton[4];
	GridBagConstraints gc = new GridBagConstraints();
	Timer timer;
	int igr = 0;
	boolean batDead = false;
	boolean bowlEmpty = false;
	boolean doorUnlocked = false;
	String position = "door";
	boolean btnlck = false;
	DoorMenu doorDialog;
	CabinetMenu cabinetDialog;
	BowlMenu bowlDialog;
	CageMenu cageDialog;
	Battle batBattle;
	
		FirstRoom(){
			ourHero = new CatCharacter(100, 10, 50);
			evilBat = new Enemy("EVIIIILLLLL bat", 50, 120, 20, 7, 70);
			startRoom = new Room(2);
			batBattle = new Battle(ourHero, evilBat);
			animations = new FRanim();
			
			myFrame = new JFrame("Alley w p... Cat");
			myFrame.setVisible(true);
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			myFrame.setSize(600, 600);
			myFrame.setResizable(false);
			myFrame.setLayout(new GridBagLayout());
			Dimension d;
			Dimension g = null;
			ImageIcon icon = new ImageIcon("floor.jpg");
			floor[0] = new ImageIcon("living room/floor.jpg");
			floor[1] = new ImageIcon("living room/floor01.jpg");
			floor[2] = new ImageIcon("living room/floor02.jpg");
			floor[3] = new ImageIcon("living room/floor03.jpg");
			floor[4] = new ImageIcon("living room/floor10.jpg");
			floor[5] = new ImageIcon("living room/floor11.jpg");
			floor[6] = new ImageIcon("living room/floor12.jpg");
			floor[7] = new ImageIcon("living room/floor13.jpg");
			floor[8] = new ImageIcon("living room/floor20.jpg");
			floor[9] = new ImageIcon("living room/floor21.jpg");
			floor[10] = new ImageIcon("living room/floor22.jpg");
			floor[11] = new ImageIcon("living room/floor23.jpg");
			floor[12] = new ImageIcon("living room/floor30.jpg");
			floor[13] = new ImageIcon("living room/floor31.jpg");
			floor[14] = new ImageIcon("living room/floor32.jpg");
			floor[15] = new ImageIcon("living room/floor33a.jpg");
			floor[16] = new ImageIcon("living room/floor33b.jpg");
			
			
			for(int i=0; i<labels.length; i++){
				labels[i] = new JLabel();
				d = labels[i].getPreferredSize();
				g = new Dimension(d.height+130, d.width+130);
				labels[i].setPreferredSize(g);
//				labels[i].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			}
			labels[0].setIcon(floor[0]);
			labels[1].setIcon(floor[1]);
			labels[2].setIcon(floor[2]);
			labels[3].setIcon(floor[5]);
			labels[4].setIcon(floor[6]);
			labels[5].setIcon(floor[7]);
			labels[6].setIcon(floor[8]);
			labels[7].setIcon(floor[9]);
			labels[8].setIcon(floor[11]);
			labels[9].setIcon(floor[12]);
			labels[10].setIcon(floor[13]);
			labels[11].setIcon(floor[14]);

			for(int i=0; i< buttons.length; i++){
				buttons[i] = new JButton();
				buttons[i].setPreferredSize(g);
				buttons[i].setBorderPainted(false);
			}
			
			buttons[0].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(!btnlck){
						
						switch(position){
						case "door":
							doorDialog = new DoorMenu(startRoom, ourHero);
							break;
						case "bowl":
						{
							btnlck = true;
							position = "door";
							walkBowlDoor();
							
						}
						break;
						case "cabinet":
						{
							btnlck = true;
							position = "door";
							walkCabinetDoor();
							
						}
						break;
						case "cage":
						{
							btnlck = true;
							position = "door";
							walkCageDoor();
							
						}
						break;
						}
					}
				}
			});
			buttons[1].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(!btnlck){
						
						switch(position){
						case "door":
						{
							btnlck = true;
							position = "cabinet";
							walkDoorCabinet();
							
						}
						break;
						case "bowl":
						{
							btnlck = true;
							position = "cabinet";
							walkBowlCabinet();
							
						}
						break;
						case "cabinet":
							cabinetDialog = new CabinetMenu(ourHero);
							break;
						case "cage":
						{
							btnlck = true;
							position = "cabinet";
							walkCageCabinet();
							
						}
						break;
						}
					}
				}
			});
			buttons[2].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(!btnlck){
						
						switch(position){
						case "door":
						{
							btnlck = true;
							position = "cage";
							walkDoorCage();
							
						}
						break;
						case "bowl":
						{
							btnlck = true;
							position = "cage";
							walkBowlCage();
							
						}
						break;
						case "cabinet":
						{
							btnlck = true;
							position = "cage";
							walkCabinetCage();
							
						}
						break;
						case "cage":
							cageDialog = new CageMenu(batBattle, ourHero, evilBat);
							break;
						}
					}
				}
			});
			buttons[3].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(!btnlck){
						
						switch(position){
						case "door":
						{
							btnlck = true;
							position = "bowl";
							walkDoorBowl();
							
						}
						break;
						case "bowl":
							bowlDialog = new BowlMenu(startRoom, ourHero);
							break;
						case "cabinet":
						{
							btnlck = true;
							position = "bowl";
							walkCabinetBowl();
							
						}
						break;
						case "cage":
						{
							btnlck = true;
							position = "bowl";
							walkCageBowl();
							
						}
						break;
						}
					}
					
				}
			});
			
			buttons[0].setIcon(animations.doorRun[0]);
			buttons[1].setIcon(floor[4]);
			buttons[2].setIcon(floor[10]);
			if(!bowlEmpty){
				buttons[3].setIcon(floor[15]);
			}
			else
				buttons[3].setIcon(floor[16]);
				
			
			for(int i = 0; i<3; i++){
				gc.gridx = i;
				gc.gridy = 0;
				myFrame.add(labels[i], gc);
			}
			
				gc.gridx = 3;
				gc.gridy = 0;
				myFrame.add(buttons[0], gc);
				
				gc.gridx = 0;
				gc.gridy = 1;
				myFrame.add(buttons[1], gc);
				
			for(int i = 3; i<6; i++){
				gc.gridx = i-2;
				gc.gridy = 1;
				myFrame.add(labels[i], gc);
			}
			
			for(int i = 6; i<8; i++){
				gc.gridx = i-6;
				gc.gridy = 2;
				myFrame.add(labels[i], gc);
			}
			
				gc.gridx = 2;
				gc.gridy = 2;
				myFrame.add(buttons[2], gc);
				
				gc.gridx = 3;
				gc.gridy = 2;
				myFrame.add(labels[8], gc);
				
			for(int i=9; i<12; i++){
				gc.gridx = i-9;
				gc.gridy = 3;
				myFrame.add(labels[i], gc);
			}
			
				gc.gridx = 3;
				gc.gridy = 3;
				myFrame.add(buttons[3], gc);

				myFrame.pack();
		}
		
		public void walkDoorBowl(){
			ActionListener action = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {						
					buttons[0].setIcon(animations.doorRun[igr]);
					igr++;
					if(igr == 4){
						buttons[0].setIcon(floor[3]);
						igr = 0;
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[5].setIcon(animations.doorRun[igr+4]);
								igr++;
								if(igr==7) {
									labels[5].setIcon(floor[7]);
									igr=0;
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[8].setIcon(animations.doorRun[igr+11]);
											igr++;
											if(igr==7){ //zeby skrecic z miski zmienic tu na 2 (doordown9 dorrun11)
												labels[8].setIcon(floor[11]);
												igr=0;
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
													buttons[3].setIcon(animations.doorRun[igr+18]);
													igr++;
													if(igr==2){
//														buttons[3].setIcon(floor[15]);
														igr=19;
														timer.stop();
														btnlck=false;
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
									
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
					
			myFrame.revalidate();
			myFrame.repaint();
			
			timer = new Timer(100, action);
			timer.start();
			
		}
		public void walkDoorCage(){ 
			ActionListener action = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {						
					buttons[0].setIcon(animations.doorRun[igr]);
					igr++;
					if(igr == 4){
						buttons[0].setIcon(floor[3]);
						igr = 0;
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[5].setIcon(animations.doorRun[igr+4]);
								igr++;
								if(igr==7) {
									labels[5].setIcon(floor[7]);
									igr=0;
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[8].setIcon(animations.doorRun[igr+11]);
											igr++;
											if(igr==2){ //zeby skrecic z miski zmienic tu na 2 (doordown9 dorrun11)
												igr=0;
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
													labels[8].setIcon(animations.turnToCage[igr]);
													igr++;
													if(igr==2){
														labels[8].setIcon(floor[11]);
														igr = 1;
														timer.stop();
														ActionListener action = new ActionListener(){
															public void actionPerformed(ActionEvent a){
																buttons[2].setIcon(animations.turnToCage[2]);
																timer.stop();
																btnlck=false;
																}
															};
															timer = new Timer(100, action);
															timer.start();
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
												
												
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
									
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
					
			myFrame.revalidate();
			myFrame.repaint();
			
			timer = new Timer(100, action);
			timer.start();
			
		}
		public void walkDoorCabinet(){
			ActionListener action = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					buttons[0].setIcon(animations.doorRun[igr]);
					igr++;
					if(igr == 4){
						buttons[0].setIcon(floor[3]);
						igr = 0;
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[5].setIcon(animations.doorRun[igr+4]);
								igr++;
								if(igr==7) {
									igr=0;
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[5].setIcon(animations.turnToCabinet[igr]);
											igr++;
											if(igr==2){
												labels[5].setIcon(floor[7]);
												igr=0;
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
														labels[4].setIcon(animations.turnToCabinet[igr+2]);
														igr++;
														if(igr==5){
															labels[4].setIcon(floor[6]);
															igr=0;
															timer.stop();
															ActionListener action = new ActionListener(){
																public void actionPerformed(ActionEvent a){
																	labels[3].setIcon(animations.turnToCabinet[igr+7]);
																	igr++;
																	if(igr==6){
																		labels[3].setIcon(floor[5]);
																		igr=0;
																		timer.stop();
																		ActionListener action = new ActionListener(){
																			public void actionPerformed(ActionEvent a){
																				buttons[1].setIcon(animations.turnToCabinet[igr+14]);
																				igr++;
																				if(igr==4){
																					igr=17;
																					timer.stop();
																					btnlck=false;
																				}
																			}
																		};
																		timer = new Timer(100, action);
																		timer.start();
																	}
																}
															};
															timer = new Timer(100, action);
															timer.start();
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
												
											}
											
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
								
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
					
		}
		public void walkBowlDoor(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[3].setIcon(animations.doorRun[igr]);
					igr--;
					if (igr == 17){
						buttons[3].setIcon(floor[15]);//MISKA
						igr = 19;
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[8].setIcon(animations.doorRun[igr-2]);
								igr--;
								if(igr==12){
									labels[8].setIcon(floor[11]);
									igr = 19;
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[5].setIcon(animations.doorRun[igr-9]);
											igr--;
											if(igr==12){
												labels[5].setIcon(floor[7]);
												igr = 19;
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
														buttons[0].setIcon(animations.doorRun[igr-16]);
														igr--;
														if(igr==15){
															igr = 0;
															timer.stop();
															btnlck=false;
															doorDialog = new DoorMenu(startRoom, ourHero);
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
					
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		public void walkBowlCage(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[3].setIcon(animations.doorRun[igr]);
					igr--;
					if(igr == 17){
						buttons[3].setIcon(floor[15]);
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[8].setIcon(animations.doorRun[igr]);
								igr--;
								if(igr==12){
									igr = 0;
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[8].setIcon(animations.turnToCage[igr]);
											igr++;
											if(igr==2){
												labels[8].setIcon(floor[11]);
												igr = 1;
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
														buttons[2].setIcon(animations.turnToCage[2]);
														timer.stop();
														btnlck=false;
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		public void walkBowlCabinet(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[3].setIcon(animations.doorRun[igr]);
					igr--;
					if(igr==17){
						buttons[3].setIcon(floor[15]);
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[8].setIcon(animations.doorRun[igr]);
								igr--;
								if(igr==10){
									labels[8].setIcon(floor[11]);
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[5].setIcon(animations.doorRun[igr]);
											igr--;
											if(igr==9){
												igr = 0;
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
														labels[5].setIcon(animations.turnToCabinet[igr]);
														igr++;
														if(igr==2){
															labels[5].setIcon(floor[7]);
															timer.stop();
															ActionListener action = new ActionListener(){
																public void actionPerformed(ActionEvent a){
																	labels[4].setIcon(animations.turnToCabinet[igr]);
																	igr++;
																	if(igr==7){
																		labels[4].setIcon(floor[6]);
																		timer.stop();
																		ActionListener action = new ActionListener(){
																			public void actionPerformed(ActionEvent a){
																				labels[3].setIcon(animations.turnToCabinet[igr]);
																				igr++;
																				if(igr==13){
																					labels[3].setIcon(floor[5]);
																					timer.stop();
																					ActionListener action = new ActionListener(){
																						public void actionPerformed(ActionEvent a){
																							buttons[1].setIcon(animations.turnToCabinet[igr]);
																							igr++;
																							if(igr==18){
																								igr=17;
																								timer.stop();
																								btnlck=false;
																							}
																						}
																					};
																					timer = new Timer(100, action);
																					timer.start();
																				}
																			}
																		};
																		timer = new Timer(100, action);
																		timer.start();
																	}
																}
															};
															timer = new Timer(100, action);
															timer.start();
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		public void walkCageBowl(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[2].setIcon(floor[10]);
					labels[8].setIcon(animations.turnToCage[igr]);
					igr--;
					if(igr==-1){
						igr = 13;
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[8].setIcon(animations.doorRun[igr]);
								igr++;
								if(igr==18){
									labels[8].setIcon(floor[11]);
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											buttons[3].setIcon(animations.doorRun[igr]);
											igr++;
											if(igr == 20){
												igr = 19;
												timer.stop();
												btnlck=false;
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		public void walkCageDoor(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[2].setIcon(floor[10]);
					labels[8].setIcon(animations.turnToCage[igr]);
					igr--;
					if(igr==-1){
						igr = 13;
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[8].setIcon(animations.doorRun[igr]);
								igr--;
								if(igr==10){
									labels[8].setIcon(floor[11]);
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[5].setIcon(animations.doorRun[igr]);
											igr--;
											if(igr==3){
												labels[5].setIcon(floor[7]);
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
														buttons[0].setIcon(animations.doorRun[igr]);
														igr--;
														if(igr==-1){
															igr = 0;
															timer.stop();
															btnlck=false;
															doorDialog = new DoorMenu(startRoom, ourHero);
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
									
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		public void walkCageCabinet(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[2].setIcon(animations.cageCabinet[igr-1]);
					igr++;
					if(igr==3){
						buttons[2].setIcon(floor[10]);
						igr = 5;
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[4].setIcon(animations.turnToCabinet[igr]);
								igr++;
								if(igr==7){
									labels[4].setIcon(floor[6]);
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[3].setIcon(animations.turnToCabinet[igr]);
											igr++;
											if(igr == 13){
												labels[3].setIcon(floor[5]);
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
														buttons[1].setIcon(animations.turnToCabinet[igr]);
														igr++;
														if(igr==18){
															timer.stop();
															igr=17;
															btnlck=false;
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		public void walkCabinetDoor(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[1].setIcon(animations.turnToCabinet[igr]);
					igr--;
					if(igr == 12){
						buttons[1].setIcon(floor[4]);
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[3].setIcon(animations.turnToCabinet[igr]);
								igr--;
								if(igr==6){
									labels[3].setIcon(floor[5]);
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[4].setIcon(animations.turnToCabinet[igr]);
											igr--;
											if(igr==1){
												labels[4].setIcon(floor[6]);
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
														labels[5].setIcon(animations.turnToCabinet[igr]);
														igr--;
														if(igr==-1){
															igr = 10;
															timer.stop();
															ActionListener action = new ActionListener(){
																public void actionPerformed(ActionEvent a){
																	labels[5].setIcon(animations.doorRun[igr]);
																	igr--;
																	if(igr==3){
																		labels[5].setIcon(floor[7]);
																		timer.stop();
																		ActionListener action = new ActionListener(){
																			public void actionPerformed(ActionEvent a){
																				buttons[0].setIcon(animations.doorRun[igr]);
																				igr--;
																				if(igr==-1){
																					igr = 0;
																					timer.stop();
																					btnlck=false;
																					doorDialog = new DoorMenu(startRoom, ourHero);
																				}
																			}
																		};
																		timer = new Timer(100, action);
																		timer.start();
																	}
																}
															};
															timer = new Timer(100, action);
															timer.start();
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		public void walkCabinetBowl(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[1].setIcon(animations.turnToCabinet[igr]);
					igr--;
					if(igr==12){
						buttons[1].setIcon(floor[4]);
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[3].setIcon(animations.turnToCabinet[igr]);
								igr--;
								if(igr==6){
									labels[3].setIcon(floor[5]);
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
										labels[4].setIcon(animations.turnToCabinet[igr]);
										igr--;
										if(igr==1){
											labels[4].setIcon(floor[6]);
											timer.stop();
											ActionListener action = new ActionListener(){
												public void actionPerformed(ActionEvent a){
													labels[5].setIcon(animations.turnToCabinet[igr]);
													igr--;
													if(igr==-1){
															igr = 10;
															timer.stop();
															ActionListener action = new ActionListener(){
																public void actionPerformed(ActionEvent a){
																	labels[5].setIcon(animations.doorRun[igr]);
																	igr++;
																	if(igr==11){
																		labels[5].setIcon(floor[7]);
																		timer.stop();
																		ActionListener action = new ActionListener(){
																			public void actionPerformed(ActionEvent a){
																				labels[8].setIcon(animations.doorRun[igr]);
																				igr++;
																				if(igr==18){
																					labels[8].setIcon(floor[11]);
																					timer.stop();
																					ActionListener action = new ActionListener(){
																						public void actionPerformed(ActionEvent a){
																							buttons[3].setIcon(animations.doorRun[igr]);
																							igr++;
																							if(igr==20){
																								igr=19;
																								timer.stop();
																								btnlck=false;
																							}
																						}
																					};
																					timer = new Timer(100, action);
																					timer.start();
																				}
																			}
																		};
																		timer = new Timer(100, action);
																		timer.start();
																	}
																}
															};
															timer = new Timer(100, action);
															timer.start();
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		public void walkCabinetCage(){
			ActionListener action = new ActionListener(){
				public void actionPerformed(ActionEvent a){
					buttons[1].setIcon(animations.turnToCabinet[igr]);
					igr--;
					if(igr==12){
						buttons[1].setIcon(floor[4]);
						timer.stop();
						ActionListener action = new ActionListener(){
							public void actionPerformed(ActionEvent a){
								labels[3].setIcon(animations.turnToCabinet[igr]);
								igr--;
								if(igr==6){
									labels[3].setIcon(floor[5]);
									timer.stop();
									ActionListener action = new ActionListener(){
										public void actionPerformed(ActionEvent a){
											labels[4].setIcon(animations.turnToCabinet[igr]);
											igr--;
											if(igr==4){
												labels[4].setIcon(floor[6]);
												igr=1;
												timer.stop();
												ActionListener action = new ActionListener(){
													public void actionPerformed(ActionEvent a){
														buttons[2].setIcon(animations.cageCabinet[igr]);
														igr--;
														if(igr==-1){
															buttons[2].setIcon(animations.turnToCage[2]);
															igr=1;
															timer.stop();
															btnlck=false;
														}
													}
												};
												timer = new Timer(100, action);
												timer.start();
											}
										}
									};
									timer = new Timer(100, action);
									timer.start();
								}
							}
						};
						timer = new Timer(100, action);
						timer.start();
					}
				}
			};
			timer = new Timer(100, action);
			timer.start();
		}
		
		
		
		
		

}
