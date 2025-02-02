package gui;

import models.interfaces.Emailable;
import models.objects.Advertisement;
import models.objects.Email;
import models.objects.Session;
import models.views.MailviewPanel;
import models.views.inboxtable.CellsActionable;
import models.views.inboxtable.MailButton;
import models.views.inboxtable.TableActionCellEditor;
import models.views.inboxtable.TableActionCellRender;
import utils.ComponentUtils;
import utils.DatabaseUtils;
import utils.UColors;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class holds the main application window. This is where the user can view, create, and manage emails.
 * This class is also responsible for handling the inbox table. These class has attributes such as:
 *
 * @author <a href="https://github.com/vianneynara">Nara</a>
 * @author <a href="https://github.com/trustacean">Edward</a>
 *
 * */

public class MailboxPage extends javax.swing.JFrame {

    private List<Emailable> emails;
    private List<Emailable> promotionEmails;
    private List<Emailable> sentEmails;
    private List<Emailable> searchedEmail;
    private final Session session;
    private final CardLayout cardSwitcher;
    private final MailviewPanel mailviewPanel = new MailviewPanel();
    private int currentEmailIndex = -1;
    private List<Emailable> currentEmailType;
    private final TableActionCellRender renderer = new TableActionCellRender();

    /**
     * Creates new form MailboxPage, this does it all initializations.
     */
    public MailboxPage(Session session) {
        this.session = session;
        emails = DatabaseUtils.getMailbox(session.getAccountUuid());
        currentEmailType = emails;

        // Initializes the components and sets the inbox table renderer.
        initComponents();
        initInboxTable();
        cardSwitcher = (CardLayout) MAIL_VIEW.getLayout();
        initForms();
        m_userMenu.setText(session.getAccountEmailAddress());

        // Updates the table with the latest emails.
        updateTable(DatabaseUtils.getMailbox(session.getAccountUuid()));

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                DatabaseUtils.updateReads(emails);
            }
        });

        // Sets background, panels, buttons colors
        ComponentUtils.setBackgroundColor(UColors.MAROON.toColor(), m_userMenu);
        ComponentUtils.setBackgroundColor(UColors.IVORY.toColor(), MAIN_CONTAINER);
        ComponentUtils.setBackgroundColor(UColors.BEIGE.toColor(),
            TOOL_BAR, MAIL_VIEW, MAIL_TOOLS, MAILBOX_FINDPANE, INBOX_PANEL, INBOX_SCROLLPANE);
        ComponentUtils.setBackgroundColor(UColors.BRIGHT_GREEN.toColor(), b_createMail, b_refresh);
        ComponentUtils.setBackgroundColor(UColors.BRIGHT_ORANGE.toColor(), i_inboxType, b_findMail);
        ComponentUtils.setBackgroundColor(UColors.BRIGHT_RED.toColor(), b_mailReport);
        ComponentUtils.setBackgroundColor(UColors.BRIGHT_ORANGE.toColor(), b_mailReply, b_markUnread);
    }

    /**
     * Initializes the form cards for MAIL_VIEW.
     * */
    private void initForms() {
        MAIL_VIEW.add(mailviewPanel, "mailview");
        JPanel emptyView = new JPanel();
        emptyView.setBackground(UColors.BEIGE.toColor());
        MAIL_VIEW.add(emptyView, "emptyview");
        cardSwitcher.show(MAIL_VIEW, "emptyview");
    }

    /**
     * Initializes the inbox table cell renderer for the mail column.
     * */
    private void initInboxTable() {
        CellsActionable event = row -> row;
        inboxTable.getColumnModel().getColumn(0).setCellRenderer(new TableActionCellRender());
        inboxTable.getColumnModel().getColumn(0).setCellEditor(new TableActionCellEditor(event));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MAIN_CONTAINER = new javax.swing.JPanel();
        TOOL_BAR = new javax.swing.JPanel();
        i_inboxType = new javax.swing.JComboBox<>();
        b_createMail = new javax.swing.JButton();
        b_refresh = new javax.swing.JButton();
        MAIL_VIEW = new javax.swing.JPanel();
        MAIL_TOOLS = new javax.swing.JPanel();
        b_mailReply = new javax.swing.JButton();
        b_mailReport = new javax.swing.JButton();
        b_markUnread = new javax.swing.JToggleButton();
        MAILBOX_FINDPANE = new javax.swing.JPanel();
        i_findMail = new javax.swing.JTextField();
        b_findMail = new javax.swing.JButton();
        INBOX_PANEL = new javax.swing.JPanel();
        INBOX_SCROLLPANE = new javax.swing.JScrollPane();
        inboxTable = new javax.swing.JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(((Email) currentEmailType.get(row)).getRead() ? UColors.BRIGHT_GREEN.toColor() : UColors.BRIGHT_ORANGE.toColor());
                }
                return c;
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                // Get the email corresponding to the current row
                Emailable email = currentEmailType.get(row);
                // Update the current email of the renderer
                renderer.setCurrentEmail(email);
                // Return the renderer
                return renderer;
            }
        };
        WINDOW_MENU_BAR = new javax.swing.JMenuBar();
        m_userMenu = new javax.swing.JMenu();
        m_configureAccount = new javax.swing.JMenuItem();
        m_signOut = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Konsmail: Mailbox");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/konsmail_icon.png"))).getImage());
        setResizable(false);

        MAIN_CONTAINER.setBackground(new java.awt.Color(255, 255, 255));

        i_inboxType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        i_inboxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Main Inbox", "Sent", "Promotions" }));
        i_inboxType.setPreferredSize(new java.awt.Dimension(76, 30));
        i_inboxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i_inboxTypeActionPerformed(evt);
            }
        });

        b_createMail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        b_createMail.setText("Create Mail");
        b_createMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_createMailActionPerformed(evt);
            }
        });

        b_refresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        b_refresh.setText("Refresh");
        b_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TOOL_BARLayout = new javax.swing.GroupLayout(TOOL_BAR);
        TOOL_BAR.setLayout(TOOL_BARLayout);
        TOOL_BARLayout.setHorizontalGroup(
            TOOL_BARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TOOL_BARLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(b_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(i_inboxType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(b_createMail, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TOOL_BARLayout.setVerticalGroup(
            TOOL_BARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TOOL_BARLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TOOL_BARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_createMail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i_inboxType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        MAIL_VIEW.setPreferredSize(new java.awt.Dimension(761, 713));
        MAIL_VIEW.setLayout(new java.awt.CardLayout());

        b_mailReply.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        b_mailReply.setText("Reply");
        b_mailReply.setEnabled(false);
        b_mailReply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_mailReplyActionPerformed(evt);
            }
        });

        b_mailReport.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        b_mailReport.setText("Report");
        b_mailReport.setEnabled(false);
        b_mailReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_mailReportActionPerformed(evt);
            }
        });

        b_markUnread.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        b_markUnread.setText("Mark Unread");
        b_markUnread.setEnabled(false);
        b_markUnread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_markUnreadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MAIL_TOOLSLayout = new javax.swing.GroupLayout(MAIL_TOOLS);
        MAIL_TOOLS.setLayout(MAIL_TOOLSLayout);
        MAIL_TOOLSLayout.setHorizontalGroup(
            MAIL_TOOLSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MAIL_TOOLSLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(MAIL_TOOLSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_mailReport, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(b_mailReply, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(b_markUnread, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        MAIL_TOOLSLayout.setVerticalGroup(
            MAIL_TOOLSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MAIL_TOOLSLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(b_mailReply, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(b_markUnread, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_mailReport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        i_findMail.setText("Search mail here...");
        i_findMail.setPreferredSize(new java.awt.Dimension(77, 30));
        i_findMail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                i_findMailFocusGained(evt);
            }
        });

        b_findMail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        b_findMail.setText("Find");
        b_findMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_findMailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MAILBOX_FINDPANELayout = new javax.swing.GroupLayout(MAILBOX_FINDPANE);
        MAILBOX_FINDPANE.setLayout(MAILBOX_FINDPANELayout);
        MAILBOX_FINDPANELayout.setHorizontalGroup(
            MAILBOX_FINDPANELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MAILBOX_FINDPANELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i_findMail, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_findMail)
                .addContainerGap())
        );
        MAILBOX_FINDPANELayout.setVerticalGroup(
            MAILBOX_FINDPANELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MAILBOX_FINDPANELayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MAILBOX_FINDPANELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(i_findMail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_findMail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        inboxTable.setBackground(UColors.IVORY.toColor());
        inboxTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Inbox"
            }
        ));
        inboxTable.setRowHeight(79);
        inboxTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        inboxTable.getTableHeader().setReorderingAllowed(false);
        inboxTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                retrieveMail();
            }
        });
        INBOX_SCROLLPANE.setViewportView(inboxTable);
        if (inboxTable.getColumnModel().getColumnCount() > 0) {
            inboxTable.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout INBOX_PANELLayout = new javax.swing.GroupLayout(INBOX_PANEL);
        INBOX_PANEL.setLayout(INBOX_PANELLayout);
        INBOX_PANELLayout.setHorizontalGroup(
            INBOX_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(INBOX_SCROLLPANE, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
        );
        INBOX_PANELLayout.setVerticalGroup(
            INBOX_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(INBOX_SCROLLPANE, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MAIN_CONTAINERLayout = new javax.swing.GroupLayout(MAIN_CONTAINER);
        MAIN_CONTAINER.setLayout(MAIN_CONTAINERLayout);
        MAIN_CONTAINERLayout.setHorizontalGroup(
            MAIN_CONTAINERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TOOL_BAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MAIN_CONTAINERLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MAIN_CONTAINERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MAILBOX_FINDPANE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(INBOX_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MAIL_VIEW, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MAIL_TOOLS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MAIN_CONTAINERLayout.setVerticalGroup(
            MAIN_CONTAINERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MAIN_CONTAINERLayout.createSequentialGroup()
                .addComponent(TOOL_BAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MAIN_CONTAINERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MAIL_VIEW, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGroup(MAIN_CONTAINERLayout.createSequentialGroup()
                        .addComponent(MAILBOX_FINDPANE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(INBOX_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(MAIL_TOOLS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        m_userMenu.setText("usr: nara@konsmail.dev");

        m_configureAccount.setText("Configure Account");
        m_configureAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_configureAccountActionPerformed(evt);
            }
        });
        m_configureAccount.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/gear_icon.png"))).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        m_userMenu.add(m_configureAccount);

        m_signOut.setText("Sign Out");
        m_signOut.setIcon(new ImageIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/signout_icon.png"))).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        m_signOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_signOutActionPerformed(evt);
            }
        });
        m_userMenu.add(m_signOut);

        WINDOW_MENU_BAR.add(m_userMenu);

        setJMenuBar(WINDOW_MENU_BAR);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MAIN_CONTAINER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MAIN_CONTAINER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("Mailbox");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void m_configureAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_configureAccountActionPerformed
        new ConfigureAccountPage(this, true, session).setVisible(true);
    }//GEN-LAST:event_m_configureAccountActionPerformed

    private void b_createMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_createMailActionPerformed
        new NewMailDialog(this, true, session).setVisible(true);
    }//GEN-LAST:event_b_createMailActionPerformed

    private void b_mailReplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_mailReplyActionPerformed
        new NewMailDialog(this, true, session, emails.get(currentEmailIndex).getSenderUuid()).setVisible(true);
    }//GEN-LAST:event_b_mailReplyActionPerformed

    private void b_mailReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_mailReportActionPerformed
        JOptionPane.showMessageDialog(
        this,
        "This email has been reported. We may take some time to process the report, thank you for your patience.",
        "Report Sent!",
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_b_mailReportActionPerformed

    private void b_findMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_findMailActionPerformed
        List<Emailable> searchedEmail = new ArrayList<>();
        String query = i_findMail.getText();
        if (query.isEmpty() || query.equals("Search mail here...")) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a query to search!",
                "No query entered!",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Search for emails that contains the query in the subject or content
        for (Emailable e : emails) {
            if (e.getSubject().contains(query) || e.getContent().contains(query)) {
                searchedEmail.add(e);
            }
        }

        // Update the table with the searched emails
        if (!searchedEmail.isEmpty()) {
            this.searchedEmail = searchedEmail;
            currentEmailType = searchedEmail;
            currentEmailIndex = -1;
            updateTable(currentEmailType);
        } else {
            JOptionPane.showMessageDialog(
                this,
                "No results found for '" + query + "'!",
                "No results found!",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_b_findMailActionPerformed

    private void b_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_refreshActionPerformed
        disableMailTools();
        DatabaseUtils.updateReads(emails);
        emails = DatabaseUtils.getMailbox(session.getAccountUuid());
        i_inboxType.setSelectedIndex(0);
        cardSwitcher.show(MAIL_VIEW, "emptyview");
        currentEmailIndex = -1;
        currentEmailType = emails;
        updateTable(emails);
    }//GEN-LAST:event_b_refreshActionPerformed

    private void m_signOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_signOutActionPerformed
        DatabaseUtils.updateReads(emails);
        this.dispose();
        new LoginPage().setVisible(true);
    }//GEN-LAST:event_m_signOutActionPerformed

    private void i_inboxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i_inboxTypeActionPerformed
        String inboxType = (String)Objects.requireNonNull(i_inboxType.getSelectedItem());
        switch (inboxType) {
            case "Promotions" -> {
                // Filter ads email in emails by using the .filter() when email is an instance of Advertisement
                // then puts it into a new ArrayList.
                promotionEmails = emails
                    .stream()
                    .filter(email -> email instanceof Advertisement)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
                currentEmailType = promotionEmails;
            }
            case "Sent" -> {
                // Filter ads email in emails by using the .filter() when email has the same senderUuid as the current
                // session accountUuid then puts it into a new ArrayList.
                sentEmails = emails
                    .stream()
                    .filter(email -> email.getSenderUuid().equals(session.getAccountUuid()))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
                currentEmailType = sentEmails;
            }
            default -> {
                currentEmailType = emails;
            }
        }
        currentEmailIndex = -1;
        updateTable(currentEmailType);
    }//GEN-LAST:event_i_inboxTypeActionPerformed

    /**
     * Method that runs whenever the {@link #b_markUnread} button is clicked. This method will set the current email and
     * initiate necessary changes to the button.
     * */
    private void b_markUnreadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_markUnreadActionPerformed
        if (currentEmailIndex > -1) {
            var email = (Email) currentEmailType.get(currentEmailIndex);
            boolean isRead = b_markUnread.isSelected();
            email.setRead(isRead);
            markUnreadInit(email);
        }
    }//GEN-LAST:event_b_markUnreadActionPerformed

    /**
     * Method to return the emails that contains the search query in the subject or content.
     * */
    private void i_findMailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_i_findMailFocusGained
        if (i_findMail.getText().equals("Search mail here...")) {
            i_findMail.setText("");
        }
    }//GEN-LAST:event_i_findMailFocusGained

    /**
     * Disables the mail tools.
     * */
    public void disableMailTools() {
        b_mailReply.setEnabled(false);
        b_mailReport.setEnabled(false);
        b_markUnread.setEnabled(false);
    }

    /**
     * Enables the mail tools.
     * */
    public void enableMailTools() {
        b_mailReply.setEnabled(true);
        b_mailReport.setEnabled(true);
        b_markUnread.setEnabled(true);
    }

    /**
     * Updates the table rows using a List of Email objects. Each row is filled with a {@link MailButton} object that
     * contains the iterated email information.
     * */
    public void updateTable(List<Emailable> emails) {
        Object[][] data = new Object[emails.size()][1];
        String[] columns = {"Inbox"};
        for (int i = 0; i < emails.size(); i++) {
            final var currentEmail = emails.get(i);
            data[i][0] = new MailButton(currentEmail);
        }
        var model = new javax.swing.table.DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        inboxTable.setModel(model);
        inboxTable.repaint();
        inboxTable.revalidate();
        initInboxTable();
    }

    /**
     * Callback to handle which row is being clicked in the inbox table. This method sets the {@link #currentEmailIndex}
     * to the row index and sets the current email in the {@link MailviewPanel} and shows the {@link #MAIL_VIEW} panel.
     * */
    private void retrieveMail() {
        this.currentEmailIndex = inboxTable.getSelectedRow();
        if (this.currentEmailIndex > -1) {
            var email = (Email) currentEmailType.get(currentEmailIndex);
            mailviewPanel.setCurrentEmail(email);

            // Set the mailview panel as sent or received
            if (email.getSenderUuid().equals(session.getAccountUuid())) {
                mailviewPanel.setAsSent();
            } else {
                mailviewPanel.setAsReceived();
            }

            // Set the mail as read / opened
            email.setRead(true);

            // Switches the card layout to the mailview panel
            cardSwitcher.show(MAIL_VIEW, "mailview");
            enableMailTools();
            markUnreadInit(email);
        }
    }

    /**
     * Sets the mailbox tool's mark unread button to the current email's read status. This will also change the
     * button label to "Mark Unread" if the email is read and "Mark Read" if the email is unread.
     * */
    private void markUnreadInit(Emailable email) {
        b_markUnread.setSelected(email.getRead());
        b_markUnread.setText(email.getRead() ? "Mark Unread" : "Mark Read");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel INBOX_PANEL;
    private javax.swing.JScrollPane INBOX_SCROLLPANE;
    private javax.swing.JPanel MAILBOX_FINDPANE;
    private javax.swing.JPanel MAIL_TOOLS;
    private javax.swing.JPanel MAIL_VIEW;
    private javax.swing.JPanel MAIN_CONTAINER;
    private javax.swing.JPanel TOOL_BAR;
    private javax.swing.JMenuBar WINDOW_MENU_BAR;
    private javax.swing.JButton b_createMail;
    private javax.swing.JButton b_findMail;
    private javax.swing.JButton b_mailReply;
    private javax.swing.JButton b_mailReport;
    private javax.swing.JToggleButton b_markUnread;
    private javax.swing.JButton b_refresh;
    private javax.swing.JTextField i_findMail;
    private javax.swing.JComboBox<String> i_inboxType;
    private javax.swing.JTable inboxTable;
    private javax.swing.JMenuItem m_configureAccount;
    private javax.swing.JMenuItem m_signOut;
    private javax.swing.JMenu m_userMenu;
    // End of variables declaration//GEN-END:variables
}
